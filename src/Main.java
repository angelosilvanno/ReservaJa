import database.Banco;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    private static final Banco banco = new Banco();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        criarTabelas();

        if (!login()) {
            System.out.println("Acesso negado.");
            banco.desconectar();
            return;
        }

        int op = -1;
        while (op != 0) {
            System.out.println("\n=== RESERVAJA ===");
            System.out.println("1 - Cadastrar Cliente | 2 - Listar Clientes");
            System.out.println("3 - Cadastrar Mesa    | 4 - Listar Mesas");
            System.out.println("5 - Nova Reserva      | 6 - Listar Reservas");
            System.out.println("7 - Novo Item Menu    | 8 - Listar Cardapio");
            System.out.println("9 - Novo Pedido       | 10 - Listar Pedidos");
            System.out.println("0 - Sair");
            op = lerInt("Opcao: ");

            switch (op) {
                case 1:
                    banco.queryUpdate("INSERT INTO clientes (nome, telefone, email) VALUES ('"
                            + lerTexto("Nome: ") + "', '" + lerTexto("Telefone: ") + "', '" + lerTexto("Email: ") + "')");
                    System.out.println("Cliente salvo!");
                    break;
                case 2:
                    listar("SELECT * FROM clientes", "id", "nome", "telefone");
                    break;
                case 3:
                    banco.queryUpdate("INSERT INTO mesas (numero, capacidade, status) VALUES ("
                            + lerInt("Numero: ") + ", " + lerInt("Capacidade: ") + ", 'Disponivel')");
                    System.out.println("Mesa salva!");
                    break;
                case 4:
                    listar("SELECT * FROM mesas", "numero", "capacidade", "status");
                    break;
                case 5:
                    int idCli = lerInt("ID Cliente: ");
                    int numMesa = lerInt("Numero Mesa: ");
                    String data = lerTexto("Data/Hora: ");
                    banco.queryUpdate("INSERT INTO reservas (id_cliente, numero_mesa, data_hora, status) VALUES ("
                            + idCli + ", " + numMesa + ", '" + data + "', 'Confirmada')");
                    banco.queryUpdate("UPDATE mesas SET status = 'Reservada' WHERE numero = " + numMesa);
                    System.out.println("Reserva confirmada!");
                    break;
                case 6:
                    listar("SELECT * FROM reservas", "id", "numero_mesa", "data_hora");
                    break;
                case 7:
                    banco.queryUpdate("INSERT INTO itens_menu (nome, preco) VALUES ('"
                            + lerTexto("Prato/Bebida: ") + "', " + lerDouble("Preco: ") + ")");
                    System.out.println("Item adicionado!");
                    break;
                case 8:
                    listar("SELECT * FROM itens_menu", "id", "nome", "preco");
                    break;
                case 9:
                    banco.queryUpdate("INSERT INTO pedidos (id_cliente, numero_mesa, itens, total) VALUES ("
                            + lerInt("ID Cliente: ") + ", " + lerInt("Mesa: ") + ", '" + lerTexto("Itens: ") + "', " + lerDouble("Total: ") + ")");
                    System.out.println("Pedido registrado!");
                    break;
                case 10:
                    listar("SELECT * FROM pedidos", "id", "itens", "total");
                    break;
                case 0:
                    System.out.println("Encerrando...");
                    break;
                default:
                    System.out.println("Opcao invalida.");
            }
        }
        banco.desconectar();
        sc.close();
    }

    private static void criarTabelas() {
        banco.queryUpdate("CREATE TABLE IF NOT EXISTS usuarios (login TEXT PRIMARY KEY, senha TEXT);");
        banco.queryUpdate("CREATE TABLE IF NOT EXISTS clientes (id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT, telefone TEXT, email TEXT);");
        banco.queryUpdate("CREATE TABLE IF NOT EXISTS mesas (numero INTEGER PRIMARY KEY, capacidade INTEGER, status TEXT);");
        banco.queryUpdate("CREATE TABLE IF NOT EXISTS reservas (id INTEGER PRIMARY KEY AUTOINCREMENT, id_cliente INTEGER, numero_mesa INTEGER, data_hora TEXT, status TEXT);");
        banco.queryUpdate("CREATE TABLE IF NOT EXISTS itens_menu (id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT, preco REAL);");
        banco.queryUpdate("CREATE TABLE IF NOT EXISTS pedidos (id INTEGER PRIMARY KEY AUTOINCREMENT, id_cliente INTEGER, numero_mesa INTEGER, itens TEXT, total REAL);");
        banco.queryUpdate("INSERT OR IGNORE INTO usuarios VALUES ('admin', '123');");
    }

    private static boolean login() {
        for (int i = 0; i < 3; i++) {
            String user = lerTexto("Usuario: ");
            String pass = lerTexto("Senha: ");
            try {
                ResultSet rs = banco.querySearch("SELECT * FROM usuarios WHERE login = '" + user + "' AND senha = '" + pass + "'");
                if (rs != null && rs.next()) {
                    System.out.println("Login realizado!");
                    return true;
                }
            } catch (SQLException e) {
                System.out.println("Erro no login.");
            }
            System.out.println("Dados invalidos.");
        }
        return false;
    }

    private static void listar(String sql, String col1, String col2, String col3) {
        try {
            ResultSet rs = banco.querySearch(sql);
            boolean achou = false;
            while (rs != null && rs.next()) {
                achou = true;
                System.out.println(col1 + ": " + rs.getString(col1) + " | " + col2 + ": " + rs.getString(col2) + " | " + col3 + ": " + rs.getString(col3));
            }
            if (!achou) {
                System.out.println("Nenhum registro encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar.");
        }
    }

    private static String lerTexto(String msg) {
        while (true) {
            System.out.print(msg);
            String txt = sc.nextLine().trim().replace("'", "");
            if (!txt.isEmpty()) return txt;
            System.out.println("Campo vazio nao permitido.");
        }
    }

    private static int lerInt(String msg) {
        while (true) {
            System.out.print(msg);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Digite um numero inteiro valido.");
            }
        }
    }

    private static double lerDouble(String msg) {
        while (true) {
            System.out.print(msg);
            try {
                return Double.parseDouble(sc.nextLine().trim().replace(",", "."));
            } catch (NumberFormatException e) {
                System.out.println("Digite um valor numerico valido.");
            }
        }
    }
}