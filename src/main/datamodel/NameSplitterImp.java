package datamodel;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Optional;

public class NameSplitterImp implements NameSplitter {
    //singleton pattern
    private static NameSplitterImp instance = null;
    private NameSplitterImp() {}
    public static NameSplitterImp getInstance() {
        if(instance == null) {
            instance = new NameSplitterImp();
        }
        return instance;
    }

    @Override
    public Optional<SplitName> split(String name) {
        String[] parts;
        String delimiter = null;
        String[] commaDelimiter = {",",";"};
        String[] unwantedChar = {"\"", "'"};

        //prepare name by trimming whitespace and removing quotes
        var cleanName = Arrays
            .stream(unwantedChar)
            .reduce(name.trim(), (n, c) -> n.replace(c, ""));

        delimiter = Arrays
            .stream(commaDelimiter)
            .filter(d -> cleanName.contains(d))
            .findFirst()
            .orElse(" ");

        switch (delimiter) {
            case ",":
                parts = splitbyComma(cleanName, ",");
                break;
            case ";":
                parts = splitbyComma(cleanName, ";");
                break;
            default:
                parts = splitbySpace(cleanName);
                break;
        }

        return Optional.of(new SplitName(parts[0], parts[1]));
    }

    // private String[] splitName(String fullName, String delimiter) {
    //     switch (delimiter) {
    //         case "\\s":
    //             return splitbySpace(fullName);
    //         case ",":
    //             return splitbyComma(fullName);
    //         default:
    //             return splitbySpace(fullName);
    //             //
    //     }
    // }

    private String[] splitbySpace(String fullName) {
        String[] parts = fullName.trim().split("\\s+");
        String[] result = new String[2];

            if (parts.length == 1) {
                result[0] = parts[0]; // last name
                result[1] = "";       // first names
            } else {
                result[0] = parts[parts.length - 1]; // last name
                StringBuilder firstNames = new StringBuilder();
                for (int i = 0; i < parts.length - 1; i++) {
                    firstNames.append(parts[i]);
                    if (i < parts.length - 2) {
                        firstNames.append(" ");
                    }
                }
                result[1] = firstNames.toString(); // first names
            }

        return result;
    }

    private String[] splitbyComma(String fullName, String delimiter) {
        String[] parts = fullName.trim().split(delimiter,2);
        String[] result = new String[2];

        parts = Arrays
            .stream(parts)
            .map(String::trim)
            .toArray(String[]::new);

        if(parts.length == 1) {
            result[0] = parts[0]; // name
            result[1] = "";       // firstNames
        } else {
            result = parts;
        }

        return result;
    }

}
