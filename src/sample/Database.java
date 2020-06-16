import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Database {

    public static void main(String[] args) throws SQLException {
        {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/S3CURUS", "root", "pass123");
            Statement statement = conn.createStatement();
            statement.executeUpdate("TRUNCATE info");

            conn.close();
        }

        List< User > list = new ArrayList< >();
        list.add(new User(1,"Tejas","MeanMachiNe", "iamtejas", "What is your Favourite Musical Duo ???","twenty-one pilots"));
        list.add(new User(2,"Aditya","Ojha", "Csgoislove", "What is your favourite Book Series ???", "harry potter"));
        list.add(new User(3,"Apurva", "Cab27", "abc", "What is your favourite Story ???", "sad story"));
        list.add(new User(4,"Antriksh", "spaceman", "pass123","What is your Favourite Musical Band ???", "cold play"));
        list.add(new User(5,"Anubhuti","BeepBeep", "BoopBoop","e", "e"));

        var INSERT_USERS_SQL = "INSERT INTO info " + " (id, nam, user_id, pass, statement, answer) VALUES " +
                " (?, ?, ?, ?, ?, ?);";

        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/S3CURUS","root","pass123");
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            connection.setAutoCommit(false);
            for (User user : list) {
                preparedStatement.setInt(1,user.getId());
                preparedStatement.setString(2, user.getName());
                preparedStatement.setString(3, user.getUser_id());
                preparedStatement.setString(4, user.getPassword());
                preparedStatement.setString(5, user.getQuestion());
                preparedStatement.setString(6, user.getAnswer());
                preparedStatement.addBatch();
            }
            int[] updateCounts = preparedStatement.executeBatch();
            System.out.println(Arrays.toString(updateCounts));
            connection.commit();
            connection.setAutoCommit(true);
        } catch (BatchUpdateException batchUpdateException) {
            printBatchUpdateException(batchUpdateException);
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    private static void printSQLException(@NotNull SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

    private static void printBatchUpdateException(@NotNull BatchUpdateException b) {

        System.err.println("----BatchUpdateException----");
        System.err.println("SQLState:  " + b.getSQLState());
        System.err.println("Message:  " + b.getMessage());
        System.err.println("Vendor:  " + b.getErrorCode());
        System.err.print("Update counts:  ");
        int[] updateCounts = b.getUpdateCounts();

        for (int updateCount : updateCounts) {
            System.err.print(updateCount + "   ");
        }
    }

    }

    class User extends Database {

        private int id;
        private String name;
        private String user_id;
        private String question;
        private String answer;
        private String password;

        User(int id, String name, String user_id, String password, String question, String answer) {
            super();
            this.id = id;
            this.name = name;
            this.user_id = user_id;
            this.password = password;
            this.question = question;
            this.answer = answer;
        }

        String getName() {
            return name;
        }
        int getId(){
            return id;
        }

        String getUser_id() {
            return user_id;
        }

        String getQuestion() {
            return question;
        }

        String getPassword() {
            return password;
        }

        String getAnswer() {
            return answer;
        }

    }
