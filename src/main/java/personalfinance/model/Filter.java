/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personalfinance.model;

import java.time.YearMonth;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Леонид
 */
public class Filter {

    public static final int STEP_DAY = 0;
    public static final int STEP_MONTH = 1;
    public static final int STEP_YEAR = 2;

    private int step;//если указан напр. Май 2022 зависит от шага (напр. месяц)
    private Date from;//начальная дата напр. 01.05.2022    00:00
    private Date to;  //конечная  дата напр. 31.05.2022    23:59

    public Filter() {
        this(STEP_MONTH);// по умолчанию надо поставить месяц!
    }

    public Filter(int step) {
        this.step = step;
        setFromTo(new GregorianCalendar());// вставляем текущую дату
    }

    public int getStep() {
        return step;
    }

    public Date getFrom() {
        return from;
    }

    public Date getTo() {
        return to;
    }

    public void next() {
        offset(1);//меняет в зависимости от шага (день, месяц, год)
    }

    public void prev() {
        offset(-1);
    }

    public void nextPeriod() {
        step += 1;
        if (step > STEP_YEAR) {
            step = STEP_DAY;
        }
        setFromTo(new GregorianCalendar());// вставляем текущую дату
    }

    public boolean check(Date date) {
        return (date.compareTo(from) > 0) && (date.compareTo(to) < 0);
    }

    private void setFromTo(Calendar calendar) {//переключатель ДАТЫ (выводит на экран)

        switch (step) {
            case STEP_DAY:
                this.from = new GregorianCalendar(
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH),
                        0, 0, 0).getTime();
                this.to = new GregorianCalendar(
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH),
                        23, 59, 59).getTime();
                break;
            case STEP_MONTH:
                //YearMonth работает с месяцами от 1-12, а Calendar работает с 0-11 поэтому прибавляем +1
                YearMonth yearMonth = YearMonth.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1);
                this.from = new GregorianCalendar(
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        1,
                        0, 0, 0).getTime();
                this.to = new GregorianCalendar(
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        yearMonth.lengthOfMonth(),// последний день данного месяца
                        23, 59, 59).getTime();
                break;
            case STEP_YEAR:
                this.from = new GregorianCalendar(
                        calendar.get(Calendar.YEAR),
                        0,
                        1,
                        0, 0, 0).getTime();
                this.to = new GregorianCalendar(
                        calendar.get(Calendar.YEAR),
                        11,// Декабрь (12 месяц: от 0 до 11)
                        31,// в Декабре 31 день
                        23, 59, 59).getTime();
        }
    }

    private void offset(int i) {//метод для смещения на заданное кол-во шагов (в зависимости от шага (день, месяц, год))
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(from);
        switch (step) {
            case STEP_DAY:
                calendar.add(Calendar.DAY_OF_MONTH, i);
                break;
            case STEP_MONTH:
                calendar.add(Calendar.MONTH, i);
                break;
            case STEP_YEAR:
                calendar.add(Calendar.YEAR, i);
        }
        setFromTo(calendar);//обновляем даты для from и to
    }
}
