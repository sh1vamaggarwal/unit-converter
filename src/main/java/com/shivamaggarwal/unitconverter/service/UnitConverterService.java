package com.shivamaggarwal.unitconverter.service;

import org.springframework.stereotype.Component;

@Component
public class UnitConverterService {

    public Double convertLength(double value, String fromUnit, String toUnit) {
        double inMeters = switch (fromUnit) {
            case "MILLIMETER", "mm", "millimeter", "Millimeter" -> LengthUnit.MILLIMETER.toMeters(value);
            case "CENTIMETER", "Centimeter", "centimeter", "cm" -> LengthUnit.CENTIMETER.toMeters(value);
            case "METER", "Meter", "meter", "m" -> value;
            case "KILOMETER", "Kilometer", "kilometer", "km" -> LengthUnit.KILOMETER.toMeters(value);
            case "INCH", "inch", "Inch", "in" -> LengthUnit.INCH.toMeters(value);
            case "FOOT", "Foot", "foot", "ft" -> LengthUnit.FOOT.toMeters(value);
            case "YARD", "Yard", "yard", "yd" -> LengthUnit.YARD.toMeters(value);
            case "MILE", "Mile", "mile", "mi" -> LengthUnit.MILE.toMeters(value);
            default -> throw new IllegalStateException("Unexpected value: " + fromUnit);
        };
        return switch (toUnit) {
            case "MILLIMETER", "mm", "millimeter", "Millimeter" -> LengthUnit.MILLIMETER.fromMeters(inMeters);
            case "CENTIMETER", "Centimeter", "centimeter", "cm" -> LengthUnit.CENTIMETER.fromMeters(inMeters);
            case "METER", "Meter", "meter", "m" -> inMeters;
            case "KILOMETER", "Kilometer", "kilometer", "km" -> LengthUnit.KILOMETER.fromMeters(inMeters);
            case "INCH", "inch", "Inch", "in" -> LengthUnit.INCH.fromMeters(inMeters);
            case "FOOT", "Foot", "foot", "ft" -> LengthUnit.FOOT.fromMeters(inMeters);
            case "YARD", "Yard", "yard", "yd" -> LengthUnit.YARD.fromMeters(inMeters);
            case "MILE", "Mile", "mile", "mi" -> LengthUnit.MILE.fromMeters(inMeters);
            default -> throw new IllegalStateException("Unexpected value: " + fromUnit);
        };
    }

    public Double convertWeight(double value, String fromUnit, String toUnit) {
        double inKilograms = switch (fromUnit) {
            case "MILLIGRAM", "mg", "milligram", "Milligram" -> WeightUnit.MILLIGRAM.toKilograms(value);
            case "GRAM", "Gram", "gram", "g" -> WeightUnit.GRAM.toKilograms(value);
            case "KILOGRAM", "Kilogram", "kilogram", "kg" -> value;
            case "OUNCE", "Ounce", "ounce", "oz" -> WeightUnit.OUNCE.toKilograms(value);
            case "POUND", "Pound", "pound", "lb", "lbs" -> WeightUnit.POUND.toKilograms(value);
            default -> throw new IllegalStateException("Unexpected value: " + fromUnit);
        };

        return switch (toUnit) {
            case "MILLIGRAM", "mg", "milligram", "Milligram" -> WeightUnit.MILLIGRAM.fromKilograms(inKilograms);
            case "GRAM", "Gram", "gram", "g" -> WeightUnit.GRAM.fromKilograms(inKilograms);
            case "KILOGRAM", "Kilogram", "kilogram", "kg" -> inKilograms;
            case "OUNCE", "Ounce", "ounce", "oz" -> WeightUnit.OUNCE.fromKilograms(inKilograms);
            case "POUND", "Pound", "pound", "lb", "lbs" -> WeightUnit.POUND.fromKilograms(inKilograms);
            default -> throw new IllegalStateException("Unexpected value: " + toUnit);
        };
    }

    public Double convertTemperature(double value, String fromUnit, String toUnit) {
        double inCelsius = switch (fromUnit) {
            case "CELSIUS", "Celsius", "celsius", "C" -> TemperatureUnit.CELSIUS.toCelsius(value);
            case "FAHRENHEIT", "Fahrenheit", "fahrenheit", "F" -> TemperatureUnit.FAHRENHEIT.toCelsius(value);
            case "KELVIN", "Kelvin", "kelvin", "K" -> TemperatureUnit.KELVIN.toCelsius(value);
            default -> throw new IllegalStateException("Unexpected value: " + fromUnit);
        };

        return switch (toUnit) {
            case "CELSIUS", "Celsius", "celsius", "C" -> TemperatureUnit.CELSIUS.fromCelsius(inCelsius);
            case "FAHRENHEIT", "Fahrenheit", "fahrenheit", "F" -> TemperatureUnit.FAHRENHEIT.fromCelsius(inCelsius);
            case "KELVIN", "Kelvin", "kelvin", "K" -> TemperatureUnit.KELVIN.fromCelsius(inCelsius);
            default -> throw new IllegalStateException("Unexpected value: " + toUnit);
        };
    }

    enum LengthUnit {
        MILLIMETER(0.001),
        CENTIMETER(0.01),
        METER(1.0),
        KILOMETER(1000.0),
        INCH(0.0254),
        FOOT(0.3048),
        YARD(0.9144),
        MILE(1609.344);

        private final double toMeters;

        LengthUnit(double toMeters) {
            this.toMeters = toMeters;
        }

        public double toMeters(double value) {
            return value * toMeters;
        }

        public double fromMeters(double meters) {
            return meters / toMeters;
        }
    }

    enum WeightUnit {
        MILLIGRAM(0.000001),
        GRAM(0.001),
        KILOGRAM(1.0),
        OUNCE(0.0283495),
        POUND(0.453592);

        private final double toKilograms;

        WeightUnit(double toKilograms) {
            this.toKilograms = toKilograms;
        }

        public double toKilograms(double value) {
            return value * toKilograms;
        }

        public double fromKilograms(double kilograms) {
            return kilograms / toKilograms;
        }
    }

    enum TemperatureUnit {
        CELSIUS {
            @Override
            public double toCelsius(double value) {
                return value;
            }

            @Override
            public double fromCelsius(double celsius) {
                return celsius;
            }
        },
        FAHRENHEIT {
            @Override
            public double toCelsius(double value) {
                return (value - 32) * 5 / 9;
            }

            @Override
            public double fromCelsius(double celsius) {
                return (celsius * 9 / 5) + 32;
            }
        },
        KELVIN {
            @Override
            public double toCelsius(double value) {
                return value - 273.15;
            }

            @Override
            public double fromCelsius(double celsius) {
                return celsius + 273.15;
            }
        };

        public abstract double toCelsius(double value);
        public abstract double fromCelsius(double celsius);
    }
}
