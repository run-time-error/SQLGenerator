package ddl;

import annotations.Column;
import annotations.Table;
import exception.DDLException;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

class TableCreator {

    private final String FIRST_BRACKET_START = "(";
    private final String FIRST_BRACKET_END = ")";
    /***
     * @param <T> The Parameter type
     * @param tClass The Class Object to get the table annotation info from.
     * @return @Table provided above the param classObject declaration.
     */
    private <T> Table getTableAnnotation(Class<T> tClass) {
        Table table = tClass.getDeclaredAnnotation(Table.class);
        if(table == null){
            throw new DDLException("No Table Name Annotation Found for " + tClass);
        }
        return table;
    }

    /***
     *
     * @param classObject class object to get the table name info from
     * @param <T> The type of parameter
     * @return String Table Name given inside @Table
     */

    <T> String getTableName(Class<T> classObject) {
        Table table = getTableAnnotation(classObject);
        String tableName = table.value().trim();
        if(tableName.isEmpty()) {
            throw new DDLException("Table Name Not Found inside Table Annotation for " + classObject);
        }
        return tableName;
    }



    /***
     *
     * @param classObject class object to get the Column Info from
     * @param <T> the type parameter
     * @return Map of all column annotated fields mapped with respected fields
     */

    <T> Map<Field, Column> getAllColumns(Class<T> classObject) {
        return Arrays.stream(classObject.getDeclaredFields())
                .filter(t->t.getDeclaredAnnotation(Column.class) != null)
                .collect(Collectors.toMap(t->t, t->t.getDeclaredAnnotation(Column.class)));

    }

    /***
     *
     * @param classObject The Class object to get the SQL statement from
     * @param <T> the type parameter
     * @return String the create table statement
     */
    <T>String getCreateTableSQL(Class<T> classObject) {
        Table table = getTableAnnotation(classObject);
        StringBuilder sb = new StringBuilder(1000);

        Map<Field, Column> mapOfColumnsToFields = getAllColumns(classObject);
        return sb.append("CREATE TABLE ")
                .append(table.dropExistingTable() ? "IF NOT EXISTS ": "")
                .append(table.value())
                .append(FIRST_BRACKET_START)
                .toString();
    }
}
