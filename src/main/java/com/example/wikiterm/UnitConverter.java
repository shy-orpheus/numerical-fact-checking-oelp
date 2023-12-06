package com.example.wikiterm;

public class UnitConverter {

    public static double convertToMeters(double measurement, String unit) {
        if(unit == null){ return -1; }
        switch (unit.toLowerCase()) {
            case "mm":
                return measurement / 1000; // Millimeters to meters
            case " mm":
                return measurement / 1000; // Millimeters to meters
            case " millimeter":
                return measurement / 1000; // Millimeters to meters
            case "millimeter":
                return measurement / 1000; // Millimeters to meters
            case " millimetre":
                return measurement / 1000; // Millimeters to meters
            case "millimetre":
                return measurement / 1000; // Millimeters to meters
            case " millimeters":
                return measurement / 1000; // Millimeters to meters
            case "millimeters":
                return measurement / 1000; // Millimeters to meters
            case " millimetres":
                return measurement / 1000; // Millimeters to meters
            case "millimetres":
                return measurement / 1000; // Millimeters to meters

            case "cm":
                return measurement / 100; // Centimeters to meters
            case " cm":
                return measurement / 100; // Centimeters to meters
            case "centimetre":
                return measurement / 100; // Centimeters to meters
            case " centimetre":
                return measurement / 100; // Centimeters to meters
            case "centimeter":
                return measurement / 100; // Centimeters to meters
            case " centimeter":
                return measurement / 100; // Centimeters to meters

            case "centimetres":
                return measurement / 100; // Centimeters to meters
            case " centimetres":
                return measurement / 100; // Centimeters to meters
            case "centimeters":
                return measurement / 100; // Centimeters to meters
            case " centimeters":
                return measurement / 100; // Centimeters to meters


            case "hi":
                return measurement * 1609.34;
            case " mi":
                return measurement * 1609.34;
            case "mile":
                return measurement * 1609.34;
            case " mile":
                return measurement * 1609.34;
            case "miles":
                return measurement * 1609.34;
            case " miles":
                return measurement * 1609.34;


            case "m":
                return measurement; // Already in meters
            case " m":
                return measurement; // Already in meters
            case "metre":
                return measurement; // Already in meters
            case " metre":
                return measurement; // Already in meters
            case "meter":
                return measurement; // Already in meters
            case " meter":
                return measurement; // Already in meters

            case "metres":
                return measurement; // Already in meters
            case " metres":
                return measurement; // Already in meters
            case "meters":
                return measurement; // Already in meters
            case " meters":
                return measurement; // Already in meters

            case "km":
                return measurement * 1000; // Kilometers to meters
            case " km":
                return measurement * 1000; // Kilometers to meters
            case "kilometer":
                return measurement * 1000; // Kilometers to meters
            case " kilometer":
                return measurement * 1000; // Kilometers to meters
            case "kilometre":
                return measurement * 1000; // Kilometers to meters
            case " kilometre":
                return measurement * 1000; // Kilometers to meters

            case "kilometers":
                return measurement * 1000; // Kilometers to meters
            case " kilometers":
                return measurement * 1000; // Kilometers to meters
            case "kilometres":
                return measurement * 1000; // Kilometers to meters
            case " kilometres":
                return measurement * 1000; // Kilometers to meters

            case "ft":
                return measurement * 0.3048;
            case "foot":
                return measurement * 0.3048;
            case "feet":
                return measurement * 0.3048;

            case " ft":
                return measurement * 0.3048;
            case " foot":
                return measurement * 0.3048;
            case " feet":
                return measurement * 0.3048;

            case "yd":
                return measurement * 0.9144;
            case "yard":
                return measurement * 0.9144;
            case "yards":
                return measurement * 0.9144;

            case " yd":
                return measurement * 0.9144;
            case " yard":
                return measurement * 0.9144;
            case " yards":
                return measurement * 0.9144;

            case "hm":
                return measurement * 100; // Hectometers to meters
            default:
                return -1; // Unsupported unit
        }
    }
}

