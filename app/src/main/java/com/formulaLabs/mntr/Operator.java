package com.formulaLabs.mntr;

public class Operator {
    private String m_brandName;
    private String m_operatorCode;
    private String m_signalStrengthLevel;
    private int m_signalStrengthDbm;

    public Operator() {
        m_brandName = "No_Brand_Name";
        m_operatorCode = "No_Operator_Code";
        m_signalStrengthLevel = "No_Signal_Strength_Level";
        m_signalStrengthDbm = -1000;
    }

    public Operator(String brandName, String operatorCode, String signalStrengthLevel, int signalStrengthDbm) {
        m_brandName = brandName;
        m_operatorCode = operatorCode;
        m_signalStrengthLevel = signalStrengthLevel;
        m_signalStrengthDbm = signalStrengthDbm;
    }

    public String getBrandName() {
        return m_brandName;
    }

    public String getOperatorCode() {
        return m_operatorCode;
    }

    public String getSignalStrengthLevel() {
        return m_signalStrengthLevel;
    }

    public int getSignalStrengthDbm() {
        return m_signalStrengthDbm;
    }

    public void setBrandName(String brandName) {
        m_brandName = brandName;
    }

    public void setOperatorCode(String operatorCode) {
        m_operatorCode = operatorCode;
    }

    public void setSignalStrengthLevel(String signalStrengthLevel) {
        m_signalStrengthLevel = signalStrengthLevel;
    }

    public void setSignalStrengthDbm(int signalStrengthDbm) {
        m_signalStrengthDbm = signalStrengthDbm;
    }
}
