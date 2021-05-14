package projeto_LP2_AED2;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Date {

    /**
     * Contrutor da Date par o current Time
     */
    public Date() {
        GregorianCalendar c = new GregorianCalendar();
        this.day = c.get(Calendar.DAY_OF_MONTH);
        this.month = c.get(Calendar.MONTH) + 1;
        this.year = c.get(Calendar.YEAR);
    }

    /**
     * Construtor Date
     * @param day - Dia
     * @param month - Mês
     * @param year - Ano
     */
    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    //GETTERS AND SETTERS
    public Date(Date begin) {
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    private int day;

    private int month;

    private int year;

    public boolean beforeDate(Date d) {
        return this.compareTo(d) < 0;
    }

    public boolean afterDate(Date d) {
        return this.compareTo(d) > 0;
    }

    public static boolean isLeapYear(int year) {
        if(year%400==0 || (year%4==0 && year%100!=0))
            return true;
        else
            return false;
    }

    public void incrementDate() {
        if(this.day < daysMonth(this.month, this.year)){
            day++;
        }else{
            if(this.month==12){
                this.day=1;
                this.month=1;
                this.year++;
            }else{
                month++;
                day=1;
            }
        }
    }

    public int differenceYears(Date d) {
        return d.year - this.year;
    }

    public int differenceMonths(Date d) {
        return 0;
    }

    public int compareTo(Date data) {
        if (this.year == data.year && this.month == data.month && this.day == data.day) {
            return 0;
        } else if (this.year == data.year && this.month == data.month && this.day != data.day) {
            return (this.day - data.day) / Math.abs(this.day - data.day);
        } else if (this.year == data.year && this.month != data.month) {
            return (this.month - data.month) / Math.abs(this.month - data.month);
        } else if (this.year != data.year) {
            return (this.year - data.year) / Math.abs(this.year - data.year);
        }
        return 0;
    }

    /**
     * Método para saber os dias do mês
     * @param month - Mês
     * @param year - Ano
     * @return
     */
    public static int daysMonth(int month, int year) {
        switch (month){
            case 11:
            case 4:
            case 6:
            case 9: return 30;
            case 2: return (isLeapYear(year) ? 29 : 28);
            default:
                return 31;
        }
    }

    /**
     * Método que retorna a diferença de dias entre dois meses diferentes
     * @param beginDay - Dia inicial
     * @param beginMonth - Mês inicial
     * @param endDay - Dia Final
     * @param endMonth - Mês final
     * @param year - Ano
     * @return
     */
    public static int daysBetweenMonths(short beginDay, short beginMonth, short endDay, short endMonth, int year){
        int diference = endMonth - beginMonth;
        int nmrDias = 0;
        int diasFaltam = 0;
        if(diference == 0)
            return endDay;
        for (int i = 0; i <= diference; i++) {
            if(i == 0){
                diasFaltam = daysMonth((int)beginMonth, (int)year) - beginDay;
                nmrDias += diasFaltam + 1;
            }else if(i == diference){
                nmrDias += endDay;
            } else{
                nmrDias += daysMonth((int)beginMonth + i, (int)year);
            }
        }
        return nmrDias;
    }

    public static int daysCrawler(Date begin, Date end){
        int diferenceAnos = end.getYear() - begin.getYear();
        short dia = 31, mes = 12, diaI = 1, mesI = 1;
        int nmrDias = 0;
        if(begin.getYear() == end.getYear())
            return daysBetweenMonths((short)begin.getDay(), (short)begin.getMonth(), (short)end.getDay(), (short)end.getMonth(), (short)begin.getYear());
        for (int i = 0; i <= diferenceAnos; i++) {
            if(i == 0){
                nmrDias += daysBetweenMonths((short)begin.getDay(), (short)begin.getMonth(), dia, mes, (short)begin.getYear());
            }else if(i == diferenceAnos){
                nmrDias += daysBetweenMonths(diaI, mesI, (short)end.getDay(), (short)end.getMonth(), (short)end.getYear());
            }else{
                if(isLeapYear(begin.getYear()+i))
                    nmrDias += 366;
                else
                    nmrDias += 365;
            }
        }
        return nmrDias;
    }

    public static long daysCrawlerRecursiveAux(Date begin, Date end){
        if(begin.beforeDate(end)){
            begin.incrementDate();
            return (1 + Date.daysCrawlerRecursiveAux(begin, end));
        }
        return 0;
    }

    public static int daysCrawlerRecursive(Date begin, Date end){
        int diffDays = 0;
        int diffYear = ((begin.month<=end.month) ? (end.year-begin.year) : (end.year-begin.year-1));
        Date auxBegin = new Date(begin), auxEnd = null;

        while (diffYear > 10){
            diffYear -= 10;
            auxEnd = new Date(auxBegin.day, auxBegin.month, auxBegin.year+10);
            diffDays += daysCrawlerRecursiveAux(auxBegin, auxEnd);
            begin.year += 10;
            auxBegin = new Date(begin);
        }

        auxEnd = new Date(end);
        diffDays += daysCrawlerRecursiveAux(auxBegin, auxEnd);
        return diffDays;
    }

    @Override
    public String toString() {
        return day + "/" + month + "/" + year;
    }

}