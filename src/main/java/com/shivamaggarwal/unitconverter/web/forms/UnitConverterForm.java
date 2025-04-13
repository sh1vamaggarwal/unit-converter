package com.shivamaggarwal.unitconverter.web.forms;

import jakarta.validation.constraints.*;

public class UnitConverterForm {

    @NotNull
    @PositiveOrZero
    Double value;
    @NotBlank
    String fromUnit, toUnit;

    public UnitConverterForm(Double value, String fromUnit, String toUnit) {
        this.value = value;
        this.fromUnit = fromUnit;
        this.toUnit = toUnit;
    }

    public UnitConverterForm() {}

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getFromUnit() {
        return fromUnit;
    }

    public void setFromUnit(String fromUnit) {
        this.fromUnit = fromUnit;
    }

    public String getToUnit() {
        return toUnit;
    }

    public void setToUnit(String toUnit) {
        this.toUnit = toUnit;
    }
}
