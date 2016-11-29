package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.company.Client.file;

/**
 * Created by tyuly on 29.11.2016.
 */
public class Bank implements Terminal {

    private Logger log = Logger.getLogger(Bank.class.getName());
    static ArrayList<Client> clients = new ArrayList<Client>();
    static ArrayList<Card> cards = new ArrayList<Card>();
    private Scanner in = new Scanner(System.in);
    private String defaultPin = "1234";

    @Override
    public double checkCount() {
        System.out.println("Введите номер счета");
        String s = in.nextLine();
        for (Card c:cards) {
            if (c.getNumber().equals(s)) {
                checkCardPin(0,c);
                return c.getCount();
            }
        }
        System.out.println("Такого счета нет");
        return 0.0;
    }

    @Override
    public void put() {
        double sum;
        System.out.println("Введите номер счета");
        String s = in.nextLine();
        for (Card c : cards) {
            if (c.getNumber().equals(s)) {
                checkCardPin(0,c);
                System.out.println("Введите сумму");
                sum = in.nextDouble();
                if (sum % 100 == 0) {
                    c.setCount(sum);
                    System.out.println("Счет пополнен");
                } else {
                    System.out.println("Невозможно пополнить счет. Сумма должна быть кратна 100");
                }
            }
        }
    }

    @Override
    public void pull() {
        double sum;
        System.out.println("Введите номер счета");
        String s = in.nextLine();
        for (Card c : cards) {
            if (c.getNumber().equals(s)) {
                checkCardPin(0,c);
                System.out.println("Введите сумму");
                sum = in.nextDouble();
                if (sum % 100 == 0) {
                    try {
                        if (c.getCount() >= sum) {
                            c.setCount(-sum);
                        } else {
                            throw new InsufficientFunds();
                        }
                    } catch (InsufficientFunds i) {
                        //log.log(Level.SEVERE, "Exception: ", i);
                        Main.menu();
                    }
                } else {
                    System.out.println("Невозможно снять деньги. Сумма должна быть кратна 100");
                }
            }
        }
    }
    @Override
    public void createClient() {
        checkDefaultPin(0);
        System.out.println("Введите имя и фамилию нового клиента");
        String s [] = in.nextLine().split("\\s");
        try {
            try {
                for (Client c : clients) {
                    if ((c.getName().equals(s[1])) && (c.getSurname().equals(s[0]))) {
                        throw new Duplicate(c);
                    }

                }
                Client client = new Client(s[0], s[1]);
                //writeClientByteFile(client);
                //writeClientToFile(client);
                FileOutputStream fos = new FileOutputStream("client" + Client.file + ".bin ");
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(client);
                oos.flush();
                oos.close();
                fos.close();
                clients.add(client);
            }
            catch (Duplicate d) {
                //log.log(Level.SEVERE, "Exception: ", d);
                Main.menu();
            }
        }
        catch (IOException e) {
            System.out.println("Ошибка");
            Main.menu();
        }
    }

    @Override
    public void deleteClient() { //удаляем клиента вместе с картами
        checkDefaultPin(0);
        System.out.println("Введите имя и фамилию клиента, которого нужно удалить");
        String s [] = in.nextLine().split("\\s");
        for (Client c:clients) {
            if ((c.getName().equals(s[1])) && (c.getSurname().equals(s[0]))) {
                for (Card card: cards) {
                    if (card.getClient().equals(c)) {
                        cards.remove(card);
                    }
                }
                clients.remove(c);
            }
        }

    }

    @Override
    public void createCard() {
        checkDefaultPin(0);
        Client client = null;
        try {
            System.out.println("Введите номер карты");
            String s = in.nextLine();
            for (Card c : cards) {
                if (c.getNumber().equals(s)) {
                    throw new Duplicate(c);
                }
            }
            System.out.println("Введите pin для карты");
            String pin = in.nextLine();
            System.out.println("Введите имя и фамилию клиента, для которого создается карта");
            String cl[] = in.nextLine().split("\\s");
            try {
                for (Client c : clients) {
                    if ((c.getName().equals(cl[1])) && (c.getSurname().equals(cl[0]))) {
                        client = c;
                    }
                }
                if (client != null) {
                    Card card = new Card(client, s, pin);
                    client.setCards(card);
                    cards.add(card);
                    //writeCardToFile(card);
                    writeCardByteFile(card);
                    FileOutputStream fos = new FileOutputStream("card" + Card.file + ".bin");
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(card);
                    oos.flush();
                    oos.close();
                    fos.close();
                }
                else {
                    System.out.println("Такого клиента нет");
                }


            }
            catch (IOException e) {
                //
                //System.out.println("Ошибка");
                log.log(Level.SEVERE, "Exception: ", e);
                Main.menu();
            }

        }
        catch (Duplicate d) {
            //log.log(Level.SEVERE, "Exception: ", d);
            Main.menu();
        }
    }

    @Override
    public void deleteCard() {
        checkDefaultPin(0);
        Card cc = null;
        System.out.println("Введите номер карты");
        String s = in.nextLine();
        for (Card c:cards) {
            if (c.getNumber().equals(s)) {
                cc = c;
            }
        }
        cards.remove(cc);
        for ( Client c: clients) {
            ArrayList<Card> cards2 = c.getCards();
            for (Card card:cards) {
                if (card.getNumber().equals(s)) {
                    cc = card;
                }
            }
            cards2.remove(cc);
        }

    }

    public  void getClients() {
        checkDefaultPin(0);
        if (Bank.clients.size() == 0) {
            System.out.println("Клиентов нет");
        }
        else {
            for (Client cl : Bank.clients) {
                System.out.print(cl.getName() + " " + cl.getSurname());
                System.out.println(" ");
            }
        }
    }

    public  void getCards() {
        checkDefaultPin(0);
        if (Bank.cards.size() == 0) {
            System.out.println("Карт нет");
        } else {
            for (Card card : Bank.cards) {
                System.out.println(card.getClient() + " " + card.getPin() + " " + card.getNumber() + " " + card.getCount());
            }
        }
    }

    public void checkDefaultPin(int k) {
        System.out.println("Введите pin");
        String pin = in.nextLine();
        boolean p = false;
        try {
            while (k <= 2) {
                k++;
                if (pin.equals(defaultPin)) {
                    break;
                } else {
                    throw new InvalidPin();
                }
            }
        }
        catch (InvalidPin i) {
            try {
                if (k <= 2) {
                    checkDefaultPin(k);
                    k++;
                } else {
                    throw new AccountLock();
                }
            }
            catch (AccountLock a) {
                Main.menu();
            }
        }

    }

    public void checkCardPin(int k, Card card) {
        System.out.println("Введите pin");
        String pin = in.nextLine();
        boolean p = false;
        try {
            while (k <= 2) {
                k++;
                if (pin.equals(card.getPin())) {
                    break;
                } else {
                    throw new InvalidPin();
                }
            }
        }
        catch (InvalidPin i) {
            try {
                if (k <= 2) {
                    checkCardPin(k,card);
                    k++;
                } else {
                    throw new AccountLock();
                }
            }
            catch (AccountLock a) {
                Main.menu();
            }
        }
    }

    public void writeCardToFile(Card card) {
        try {
            PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter("card.txt")));
            printWriter.println(card.getNumber() + " " + card.getPin() + " " + card.getCount() + " " + card.getClient().getName() + " " + card.getClient().getSurname());
            printWriter.close();
        } catch (IOException e) {
            System.out.println("Ошибка записи");
            Main.menu();
        }
    }

    public void writeClientToFile(Client client) {
        try {
            PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter("client.txt")));
            printWriter.println(client.getName() + " " + client.getSurname());
            printWriter.close();
        } catch (IOException e) {
            System.out.println("Ошибка записи");
            Main.menu();
        }
    }

    public void readCard () {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("card.txt"));
            StreamTokenizer st = new StreamTokenizer(bufferedReader);
            //String s [] = bufferedReader.readLine().split("\\s");
            String s = "";
            while (st.nextToken() != StreamTokenizer.TT_EOF) {
                s += st.sval;
                s +=" ";
            }
            System.out.println(s);
        } catch (IOException e) {
            System.out.println("Ошибка чтения");
            Main.menu();
        }
    }

    public void readClient () {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("client.txt"));
            StreamTokenizer st = new StreamTokenizer(bufferedReader);
           // String s [] = bufferedReader.readLine().split("\\s");
           // Client client = new Client(s[1],s[0]);
            //clients.add(client);
            String s = "";
            while (st.nextToken() != StreamTokenizer.TT_EOF) {
                s += st.sval;
                s +=" ";
            }
            System.out.println(s);
        } catch (IOException e) {
            System.out.println("Ошибка чтения");
            Main.menu();
        }
    }

    public void writeCardByteFile(Card card) {
        try {
            DataOutputStream out = new DataOutputStream(new FileOutputStream("card.bin")); //файл
            //DataOutputStream out = new DataOutputStream(System.out); //консоль
            out.writeUTF(card.getNumber());
            out.writeUTF(card.getPin());
            out.writeUTF(card.getClient().getName());
            out.writeUTF(card.getClient().getSurname());
            out.writeDouble(card.getCount());
            out.close();
        } catch (IOException i) {
            System.out.println("Ошибка");
        }
    }

    public void writeClientByteFile(Client client) {
        try {
            DataOutputStream out = new DataOutputStream(new FileOutputStream("client.bin"));
            //DataOutputStream out = new DataOutputStream(System.out);
            out.writeUTF(client.getName());
            out.writeUTF(client.getSurname());
            out.close();
        }
        catch (IOException i) {
            System.out.println("Ошибка");
        }

    }

    public void readCardByte () {
        try {
            DataInputStream in = new DataInputStream(new FileInputStream("card.bin"));
            String num = in.readUTF();
            String pas = in.readUTF();
            String name = in.readUTF();
            String surname = in.readUTF();
            in.close();
        }
        catch(IOException e) {
            System.out.println("Some error occurred!");
        }

    }

    public void readClientByte () {
        try {
            DataInputStream in = new DataInputStream(new FileInputStream("client.bin"));
            String name = in.readUTF();
            String surname = in.readUTF();
            in.close();
        }
        catch(IOException e) {
            System.out.println("Some error occurred!");
        }

    }

}











