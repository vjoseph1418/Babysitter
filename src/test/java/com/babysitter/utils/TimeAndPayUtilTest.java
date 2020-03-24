package com.babysitter.utils;

import com.babysitter.exception.InvalidTimeFormatException;
import com.babysitter.service.TimeAndPayService;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class TimeAndPayUtilTest {

    TimeAndPayService timeAndPayService;

    @Before
    public void setUp() {
        timeAndPayService = new TimeAndPayService();
    }

    @Test
    public void whenGetDifferenceInHoursIsCalledAndTheTotalDifferenceInHoursIsFullThenTheTotalNumberOfHoursCalculatedIsFullAndIsReturned() throws InvalidTimeFormatException {
        LocalDateTime startDateTime = DateUtil.convertStringIntoLocalDateTime("2020-03-21 18:00");
        LocalDateTime endDateTime = DateUtil.convertStringIntoLocalDateTime("2020-03-21 19:00");
        Integer result = timeAndPayService.getDifferenceInHours(startDateTime, endDateTime);
        assertEquals(1, result, 0.01);
    }

    @Test
    public void whenGetDifferenceInHoursIsCalledAndTheTotalDifferenceInHoursIsFractionalThenTheTotalNumberOfHoursCalculatedIsFullAndIsReturned() throws InvalidTimeFormatException {
        LocalDateTime startDateTime = DateUtil.convertStringIntoLocalDateTime("2020-03-21 18:00");
        LocalDateTime endDateTime = DateUtil.convertStringIntoLocalDateTime("2020-03-21 20:01");
        Integer result = timeAndPayService.getDifferenceInHours(startDateTime, endDateTime);
        assertEquals(2, result, 0.01);
    }

    @Test
    public void whenCalculatePaymentBasedOnHoursIsCalledThenPaymentCalculatedIsReturned() {
        Integer result = timeAndPayService.calculatePaymentBasedOnHours(2, 15);
        assertEquals(30, result, 0.01);
    }

    @Test
    public void whenGetTotalPayForSingleTimeLimitIsCalledWhereStartAndEndDateTimesAreEqualThenTotalPayIsCalculatedCorrectlyAndReturned() throws InvalidTimeFormatException {
        LocalDateTime startDateTime = DateUtil.convertStringIntoLocalDateTime("2020-03-21 17:00");
        LocalDateTime endDateTime = DateUtil.convertStringIntoLocalDateTime("2020-03-21 17:00");
        LocalDateTime limitDateTime = DateUtil.convertStringIntoLocalDateTime("2020-03-21 23:00");
        Integer payBeforeLimit = 10;
        Integer payAfterLimit = 15;

        Integer result = timeAndPayService.getTotalPayForSingleTimeLimit(startDateTime, endDateTime, limitDateTime, payBeforeLimit, payAfterLimit);
        assertEquals(0, result, 0.01);
    }

    @Test
    public void whenGetTotalPayForSingleTimeLimitIsCalledWhereStartAndEndDateTimesAreBeforeTheLimitDateTimeThenTotalPayIsCalculatedCorrectlyAndReturned() throws InvalidTimeFormatException {
        LocalDateTime startDateTime = DateUtil.convertStringIntoLocalDateTime("2020-03-21 17:00");
        LocalDateTime endDateTime = DateUtil.convertStringIntoLocalDateTime("2020-03-21 23:00");
        LocalDateTime limitDateTime = DateUtil.convertStringIntoLocalDateTime("2020-03-21 23:00");
        Integer payBeforeLimit = 10;
        Integer payAfterLimit = 15;

        Integer result = timeAndPayService.getTotalPayForSingleTimeLimit(startDateTime, endDateTime, limitDateTime, payBeforeLimit, payAfterLimit);
        assertEquals(60, result, 0.01);
    }

    @Test
    public void whenGetTotalPayForSingleTimeLimitIsCalledWhereLimitDateTimeIsBetweenStartAndEndDateTimesThenTotalPayIsCalculatedCorrectlyAndReturned() throws InvalidTimeFormatException {
        LocalDateTime startDateTime = DateUtil.convertStringIntoLocalDateTime("2020-03-21 17:00");
        LocalDateTime endDateTime = DateUtil.convertStringIntoLocalDateTime("2020-03-22 00:00");
        LocalDateTime limitDateTime = DateUtil.convertStringIntoLocalDateTime("2020-03-21 23:00");
        Integer payBeforeLimit = 10;
        Integer payAfterLimit = 15;

        Integer result = timeAndPayService.getTotalPayForSingleTimeLimit(startDateTime, endDateTime, limitDateTime, payBeforeLimit, payAfterLimit);
        assertEquals(75, result, 0.01);
    }

    @Test
    public void whenGetTotalPayForSingleTimeLimitIsCalledWhereStartDateTimeEndDateTimeAndLimitDateTimeAreAllEqualThenTotalPayIsCalculatedCorrectlyAndReturned() throws InvalidTimeFormatException {
        LocalDateTime startDateTime = DateUtil.convertStringIntoLocalDateTime("2020-03-21 23:00");
        LocalDateTime endDateTime = DateUtil.convertStringIntoLocalDateTime("2020-03-21 23:00");
        LocalDateTime limitDateTime = DateUtil.convertStringIntoLocalDateTime("2020-03-21 23:00");
        Integer payBeforeLimit = 10;
        Integer payAfterLimit = 15;

        Integer result = timeAndPayService.getTotalPayForSingleTimeLimit(startDateTime, endDateTime, limitDateTime, payBeforeLimit, payAfterLimit);
        assertEquals(0, result, 0.01);
    }

    @Test
    public void whenGetTotalPayForSingleTimeLimitIsCalledWhereStartAndEndDateTimeAreAfterTheLimitDateTimeThenTotalPayIsCalculatedCorrectlyAndReturned() throws InvalidTimeFormatException {
        LocalDateTime startDateTime = DateUtil.convertStringIntoLocalDateTime("2020-03-22 00:00");
        LocalDateTime endDateTime = DateUtil.convertStringIntoLocalDateTime("2020-03-22 02:00");
        LocalDateTime limitDateTime = DateUtil.convertStringIntoLocalDateTime("2020-03-21 23:00");
        Integer payBeforeLimit = 10;
        Integer payAfterLimit = 15;

        Integer result = timeAndPayService.getTotalPayForSingleTimeLimit(startDateTime, endDateTime, limitDateTime, payBeforeLimit, payAfterLimit);
        assertEquals(30, result, 0.01);
    }

    @Test
    public void whenGetTotalPayForSingleTimeLimitIsCalledWhereStartTimeEqualToLimitDateTimeAndEndDateTimeIsAfterLimitDateTimeThenTotalPayIsCalculatedCorrectlyAndReturned() throws InvalidTimeFormatException {
        LocalDateTime startDateTime = DateUtil.convertStringIntoLocalDateTime("2020-03-21 23:00");
        LocalDateTime endDateTime = DateUtil.convertStringIntoLocalDateTime("2020-03-22 04:00");
        LocalDateTime limitDateTime = DateUtil.convertStringIntoLocalDateTime("2020-03-21 23:00");
        Integer payBeforeLimit = 10;
        Integer payAfterLimit = 15;

        Integer result = timeAndPayService.getTotalPayForSingleTimeLimit(startDateTime, endDateTime, limitDateTime, payBeforeLimit, payAfterLimit);
        assertEquals(75, result, 0.01);
    }

    @Test
    public void whenGetTotalPayForSingleTimeLimitIsCalledWhereStartTimeIsFivePMAndEndTimeIsFourAMThenTotalPayIsCalculatedCorrectlyAndReturned() throws InvalidTimeFormatException {
        LocalDateTime startDateTime = DateUtil.convertStringIntoLocalDateTime("2020-03-21 17:00");
        LocalDateTime endDateTime = DateUtil.convertStringIntoLocalDateTime("2020-03-22 04:00");
        LocalDateTime limitDateTime = DateUtil.convertStringIntoLocalDateTime("2020-03-21 23:00");
        Integer payBeforeLimit = 10;
        Integer payAfterLimit = 15;

        Integer result = timeAndPayService.getTotalPayForSingleTimeLimit(startDateTime, endDateTime, limitDateTime, payBeforeLimit, payAfterLimit);
        assertEquals(135, result, 0.01);
    }

    @Test
    public void whenGetTotalPayForDoubleTimeLimitIsCalledWhereStartAndEndTimeAreEqualThenTotalPayIsCalculatedAndReturned() throws InvalidTimeFormatException {
        LocalDateTime startDateTime = DateUtil.convertStringIntoLocalDateTime("2020-03-21 18:00");
        LocalDateTime endDateTime = DateUtil.convertStringIntoLocalDateTime("2020-03-21 18:00");
        LocalDateTime firstLimit = DateUtil.convertStringIntoLocalDateTime("2020-03-21 22:00");
        LocalDateTime secondLimit = DateUtil.convertStringIntoLocalDateTime("2020-03-22 00:00");
        Integer payPerHourBeforeFirstLimit = 15;
        Integer payPerHourBetweenLimits = 12;
        Integer payPerHourAfterSecondLimit = 21;

        Integer result = timeAndPayService.getTotalPayForDoubleTimeLimit(startDateTime, endDateTime, firstLimit, secondLimit, payPerHourBeforeFirstLimit, payPerHourBetweenLimits, payPerHourAfterSecondLimit);
        assertEquals(0, result, 0.01);
    }

    @Test
    public void whenGetTotalPayForDoubleTimeLimitIsCalledWhereEndTimeIsBeforeTheFirstLimitThenTotalPayIsCalculatedAndReturned() throws InvalidTimeFormatException {
        LocalDateTime startDateTime = DateUtil.convertStringIntoLocalDateTime("2020-03-21 17:00");
        LocalDateTime endDateTime = DateUtil.convertStringIntoLocalDateTime("2020-03-21 21:00");
        LocalDateTime firstLimit = DateUtil.convertStringIntoLocalDateTime("2020-03-21 22:00");
        LocalDateTime secondLimit = DateUtil.convertStringIntoLocalDateTime("2020-03-22 00:00");
        Integer payPerHourBeforeFirstLimit = 15;
        Integer payPerHourBetweenLimits = 12;
        Integer payPerHourAfterSecondLimit = 21;

        Integer result = timeAndPayService.getTotalPayForDoubleTimeLimit(startDateTime, endDateTime, firstLimit, secondLimit, payPerHourBeforeFirstLimit, payPerHourBetweenLimits, payPerHourAfterSecondLimit);
        assertEquals(60, result, 0.01);
    }

    @Test
    public void whenGetTotalPayForDoubleTimeLimitIsCalledWhereEndTimeIsEqualToTheFirstLimitThenTotalPayIsCalculatedAndReturned() throws InvalidTimeFormatException {
        LocalDateTime startDateTime = DateUtil.convertStringIntoLocalDateTime("2020-03-21 17:00");
        LocalDateTime endDateTime = DateUtil.convertStringIntoLocalDateTime("2020-03-21 22:00");
        LocalDateTime firstLimit = DateUtil.convertStringIntoLocalDateTime("2020-03-21 22:00");
        LocalDateTime secondLimit = DateUtil.convertStringIntoLocalDateTime("2020-03-22 00:00");
        Integer payPerHourBeforeFirstLimit = 15;
        Integer payPerHourBetweenLimits = 12;
        Integer payPerHourAfterSecondLimit = 21;

        Integer result = timeAndPayService.getTotalPayForDoubleTimeLimit(startDateTime, endDateTime, firstLimit, secondLimit, payPerHourBeforeFirstLimit, payPerHourBetweenLimits, payPerHourAfterSecondLimit);
        assertEquals(75, result, 0.01);
    }

    @Test
    public void whenGetTotalPayForDoubleTimeLimitIsCalledWhereEndTimeIsBetweenTheLimitsThenTotalPayIsCalculatedAndReturned() throws InvalidTimeFormatException {
        LocalDateTime startDateTime = DateUtil.convertStringIntoLocalDateTime("2020-03-21 17:00");
        LocalDateTime endDateTime = DateUtil.convertStringIntoLocalDateTime("2020-03-21 23:00");
        LocalDateTime firstLimit = DateUtil.convertStringIntoLocalDateTime("2020-03-21 22:00");
        LocalDateTime secondLimit = DateUtil.convertStringIntoLocalDateTime("2020-03-22 00:00");
        Integer payPerHourBeforeFirstLimit = 15;
        Integer payPerHourBetweenLimits = 12;
        Integer payPerHourAfterSecondLimit = 21;

        Integer result = timeAndPayService.getTotalPayForDoubleTimeLimit(startDateTime, endDateTime, firstLimit, secondLimit, payPerHourBeforeFirstLimit, payPerHourBetweenLimits, payPerHourAfterSecondLimit);
        assertEquals(87, result, 0.01);
    }

    @Test
    public void whenGetTotalPayForDoubleTimeLimitIsCalledWhereEndTimeIsEqualToTheSecondLimitThenTotalPayIsCalculatedAndReturned() throws InvalidTimeFormatException {
        LocalDateTime startDateTime = DateUtil.convertStringIntoLocalDateTime("2020-03-21 17:00");
        LocalDateTime endDateTime = DateUtil.convertStringIntoLocalDateTime("2020-03-22 00:00");
        LocalDateTime firstLimit = DateUtil.convertStringIntoLocalDateTime("2020-03-21 22:00");
        LocalDateTime secondLimit = DateUtil.convertStringIntoLocalDateTime("2020-03-22 00:00");
        Integer payPerHourBeforeFirstLimit = 15;
        Integer payPerHourBetweenLimits = 12;
        Integer payPerHourAfterSecondLimit = 21;

        Integer result = timeAndPayService.getTotalPayForDoubleTimeLimit(startDateTime, endDateTime, firstLimit, secondLimit, payPerHourBeforeFirstLimit, payPerHourBetweenLimits, payPerHourAfterSecondLimit);
        assertEquals(99, result, 0.01);
    }

    @Test
    public void whenGetTotalPayForDoubleTimeLimitIsCalledWhereStartTimeIsBeforeTheFirstLimitAndEndTimeIsAfterTheSecondLimitThenTotalPayCalculatedIsReturned() throws InvalidTimeFormatException {
        LocalDateTime startDateTime = DateUtil.convertStringIntoLocalDateTime("2020-03-21 17:00");
        LocalDateTime endDateTime = DateUtil.convertStringIntoLocalDateTime("2020-03-22 01:00");
        LocalDateTime firstLimit = DateUtil.convertStringIntoLocalDateTime("2020-03-21 22:00");
        LocalDateTime secondLimit = DateUtil.convertStringIntoLocalDateTime("2020-03-22 00:00");
        Integer payPerHourBeforeFirstLimit = 15;
        Integer payPerHourBetweenLimits = 12;
        Integer payPerHourAfterSecondLimit = 21;

        Integer result = timeAndPayService.getTotalPayForDoubleTimeLimit(startDateTime, endDateTime, firstLimit, secondLimit, payPerHourBeforeFirstLimit, payPerHourBetweenLimits, payPerHourAfterSecondLimit);
        assertEquals(111, result, 0.01);
    }

    @Test
    public void whenGetTotalPayForDoubleTimeLimitIsCalledWhereStartTimeIsEqualToTheFirstLimitAndEndTimeIsAfterTheSecondLimitThenTotalPayCalculatedIsReturned() throws InvalidTimeFormatException {
        LocalDateTime startDateTime = DateUtil.convertStringIntoLocalDateTime("2020-03-21 22:00");
        LocalDateTime endDateTime = DateUtil.convertStringIntoLocalDateTime("2020-03-22 01:00");
        LocalDateTime firstLimit = DateUtil.convertStringIntoLocalDateTime("2020-03-21 22:00");
        LocalDateTime secondLimit = DateUtil.convertStringIntoLocalDateTime("2020-03-22 00:00");
        Integer payPerHourBeforeFirstLimit = 15;
        Integer payPerHourBetweenLimits = 12;
        Integer payPerHourAfterSecondLimit = 21;

        Integer result = timeAndPayService.getTotalPayForDoubleTimeLimit(startDateTime, endDateTime, firstLimit, secondLimit, payPerHourBeforeFirstLimit, payPerHourBetweenLimits, payPerHourAfterSecondLimit);
        assertEquals(45, result, 0.01);
    }

    @Test
    public void whenGetTotalPayForDoubleTimeLimitIsCalledWhereStartTimeIsBetweenTheLimitsAndEndTimeIsAfterTheSecondLimitThenTotalPayCalculatedIsReturned() throws InvalidTimeFormatException {
        LocalDateTime startDateTime = DateUtil.convertStringIntoLocalDateTime("2020-03-21 23:00");
        LocalDateTime endDateTime = DateUtil.convertStringIntoLocalDateTime("2020-03-22 01:00");
        LocalDateTime firstLimit = DateUtil.convertStringIntoLocalDateTime("2020-03-21 22:00");
        LocalDateTime secondLimit = DateUtil.convertStringIntoLocalDateTime("2020-03-22 00:00");
        Integer payPerHourBeforeFirstLimit = 15;
        Integer payPerHourBetweenLimits = 12;
        Integer payPerHourAfterSecondLimit = 21;

        Integer result = timeAndPayService.getTotalPayForDoubleTimeLimit(startDateTime, endDateTime, firstLimit, secondLimit, payPerHourBeforeFirstLimit, payPerHourBetweenLimits, payPerHourAfterSecondLimit);
        assertEquals(33, result, 0.01);
    }

    @Test
    public void whenGetTotalPayForDoubleTimeLimitIsCalledWhereStartTimeAndEndTimeAreEqualToTheSecondLimitThenTotalPayIsZero() throws InvalidTimeFormatException {
        LocalDateTime startDateTime = DateUtil.convertStringIntoLocalDateTime("2020-03-21 22:00");
        LocalDateTime endDateTime = DateUtil.convertStringIntoLocalDateTime("2020-03-21 22:00");
        LocalDateTime firstLimit = DateUtil.convertStringIntoLocalDateTime("2020-03-21 22:00");
        LocalDateTime secondLimit = DateUtil.convertStringIntoLocalDateTime("2020-03-22 00:00");
        Integer payPerHourBeforeFirstLimit = 15;
        Integer payPerHourBetweenLimits = 12;
        Integer payPerHourAfterSecondLimit = 21;

        Integer result = timeAndPayService.getTotalPayForDoubleTimeLimit(startDateTime, endDateTime, firstLimit, secondLimit, payPerHourBeforeFirstLimit, payPerHourBetweenLimits, payPerHourAfterSecondLimit);
        assertEquals(0, result, 0.01);
    }

    @Test
    public void whenGetTotalPayForDoubleTimeLimitIsCalledWhereStartTimeAndEndTimeAreAfterTheSecondLimitThenTotalPayCalculatedIsReturned() throws InvalidTimeFormatException {
        LocalDateTime startDateTime = DateUtil.convertStringIntoLocalDateTime("2020-03-22 01:00");
        LocalDateTime endDateTime = DateUtil.convertStringIntoLocalDateTime("2020-03-22 04:00");
        LocalDateTime firstLimit = DateUtil.convertStringIntoLocalDateTime("2020-03-21 22:00");
        LocalDateTime secondLimit = DateUtil.convertStringIntoLocalDateTime("2020-03-22 00:00");
        Integer payPerHourBeforeFirstLimit = 15;
        Integer payPerHourBetweenLimits = 12;
        Integer payPerHourAfterSecondLimit = 21;

        Integer result = timeAndPayService.getTotalPayForDoubleTimeLimit(startDateTime, endDateTime, firstLimit, secondLimit, payPerHourBeforeFirstLimit, payPerHourBetweenLimits, payPerHourAfterSecondLimit);
        assertEquals(63, result, 0.01);
    }




}
