package fishstock;

import fishstock.FishStock.Keyword;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Encapsulates parsing methods.
 */
class Parser {
    protected static DateTimeFormatter inDateFormat = DateTimeFormatter.ofPattern("d/M/yyyy H:m");
    protected static DateTimeFormatter outDateFormat = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");

    /**
     * Check if given input starts with a keyword.
     * @param keyword The starting keyword.
     * @param input The input to compare.
     * @return The check result.
     */
    protected static boolean startsWith(String keyword, String input) {
        return input.length() >= keyword.length() && keyword.equals(input.substring(0, keyword.length()));
    }

    /**
     * Parses input into their respective commands.
     * @param input The input that starts with the command.
     * @return The keyword of the command.
     */
    protected static Keyword parse(String input) {
        if ("bye".equals(input)) {
            return Keyword.BYE;
        } else if ("list".equals(input)) {
            return Keyword.LIST;
        } else if (startsWith("mark", input)) {
            return Keyword.MARK;
        } else if (startsWith("unmark", input)) {
            return Keyword.UNMARK;
        } else if (startsWith("delete", input)) {
            return Keyword.DELETE;
        } else if (startsWith("find", input)) {
            return Keyword.FIND;
        } else if (startsWith(Todo.keyword, input)) {
            return Keyword.TODO;
        } else if (startsWith(Deadline.keyword, input)) {
            return Keyword.DEADLINE;
        } else if (startsWith(Event.keyword, input)) {
            return Keyword.EVENT;
        }
        return Keyword.INVALID;
    }

    /**
     * Gets index number from input string.
     * Has the format "[command] [task_number]".
     * Subtracts 1 from task_number to obtain index number for array.
     * @param input The input from user.
     * @return The resulting index number.
     * @throws FishStockException The exceptions while calculating the index number.
     */
    protected static Integer getTaskFromIndex(String input) throws FishStockException {
        try {
            int num = Integer.parseInt(input.split(" ", 2)[1]);
            return num - 1;

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new FishStockException("OH NOSE! Task number cannot be empty..");
        } catch (NumberFormatException e) {
            throw new FishStockException("OH NOSE! Task number has to be an integer..");
        }
    }

    /**
     * Parses date string into LocalDateTime object.
     * Has the format "[dd/mm/yyyy] [hh:mm]".
     * @param date The date string.
     * @return The LocalDateTime object.
     * @throws FishStockException The exceptions while parsing the date.
     */
    protected static LocalDateTime parseDate(String date) throws FishStockException {
        try {
            return LocalDateTime.parse(date, inDateFormat);
        } catch (DateTimeParseException e) {
            throw new FishStockException("OH NOSE! Dates should be of the format <dd/mm/yyyy hh:mm>");
        }
    }

    /**
     * Converts LocalDateTime object into String input format.
     * @param date The LocalDateTime object.
     * @return The String with input format.
     */
    protected static String inDate(LocalDateTime date) {
        return date.format(inDateFormat);
    }

    /**
     * Converts LocalDateTime object into String output format.
     * @param date The LocalDateTime object.
     * @return The String with output format.
     */
    protected static String outDate(LocalDateTime date) {
        return date.format(outDateFormat);
    }
}
