import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Main{
    private static final int DATA_PARAMETERS_COUNT = 6;

    public static void main(String[] args) 
    {
        try {
            UserDataReading();
        } catch (InputMismatchException ex) {
            System.out.println("Входные данные введены не полностью: " + ex.getMessage());
        } catch (DateTimeParseException ex) {
            System.out.println("Формат даты рождения задан неверно: " + ex.getMessage());
        } catch (NumberFormatException ex) {
            System.out.println("Формат номера телефона задан неверно: " + ex.getMessage());
        } catch (IllegalArgumentException ex) {
            System.out.println("Значение пола задано неверно: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Работа с файлом вызвала ошибку: " + ex.getMessage());
        }
        
        private static void UserDataReading() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите  имя, фамилию, отчество, дату рождения (формат dd.mm.yyyy), номер телефона (без разделителей) и пол(f или m), разделенные пробелом: ");
        String inputLine = scanner.nextLine();

        String[] dataArray = inputLine.split(" ");
        if (dataArray.length != DATA_PARAMETERS_COUNT) {
            throw new InputMismatchException("Входные данные введены не полностью: ");
        }

        String name = dataArray[0];
        String surname = dataArray[1];
        String secondName = dataArray[2];
        LocalDate dateOfBirth = DateParsing(dataArray[3]);
        long telephone = TelephoneParsing(dataArray[4]);
        char sex = SexParsing(dataArray[5]);

        String nameOfFile = "D:\\slavabelov\\Exceptions_3_Seminar\\blob\\main\\Homework3\\" + name + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nameOfFile, true))) {
            String dataOfUser = "(" + name + "(" + ")" + surname + "(" + ")" + secondName + "(" + ")" + dateOfBirth + "(" + ")" +
                    telephone + "(" + ")" + sex + ")";
            writer.write(dataOfUser);
            writer.newLine();
        }

        System.out.println("Успешно записали данные в файл: " + nameOfFile);
    }

    private static LocalDate DateParsing(String dateStr) {
        try {
            return LocalDate.parse(dateStr, java.time.format.DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        } catch (DateTimeParseException e) {
            throw new DateTimeParseException("Неверный формат даты рождения", dateStr, e.getErrorIndex());
        }
    }

    private static long TelephoneParsing(String stringPhone) {
        try {
            return Long.parseLong(stringPhone);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Формат номера телефона задан неверно: ");
        }
    }

    private static char SexParsing(String stringSex) {
        char sex = stringSex.charAt(0);
        if (sex != 'm' && sex != 'w') {
            throw new IllegalArgumentException("Значение пола задано неверно: ");
        }
        return sex;
    }

}
