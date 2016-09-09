package iii_conventions

import java.util.*

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int): Comparable<MyDate> {

    override fun compareTo(other: MyDate): Int {
        val diff:Long = GregorianCalendar(year, month, dayOfMonth).timeInMillis - GregorianCalendar(other.year, other.month, other.dayOfMonth).timeInMillis

        if (diff.equals(0)) {
            return 0
        } else if(diff > 0) {
            return 1
        }
        return -1
    }

}

class RepeatedTimeInterval(val ti: TimeInterval, val n: Int)

operator fun RepeatedTimeInterval.times(n: Int): RepeatedTimeInterval {
    return RepeatedTimeInterval(this.ti, this.n * n)
}

operator fun MyDate.rangeTo(other: MyDate): DateRange =
    DateRange(this, other)

operator fun MyDate.plus(repeatedTimeInterval: RepeatedTimeInterval): MyDate {
    return this.addTimeIntervals(repeatedTimeInterval.ti, repeatedTimeInterval.n)
}

operator fun MyDate.plus(interval: TimeInterval): MyDate {
    return this.addTimeIntervals(interval, 1)
}

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

operator fun TimeInterval.times(n: Int): RepeatedTimeInterval {
    return RepeatedTimeInterval(this, n)
}

class DateIterator(val dateRange: DateRange): Iterator<MyDate> {
    var current: MyDate = dateRange.start

    override fun hasNext(): Boolean = current <= dateRange.endInclusive

    override fun next(): MyDate {
        val result = current
        current = result.nextDay()
        return result
    }
}

class DateRange(val start: MyDate, val endInclusive: MyDate): Iterable<MyDate> {

    override fun iterator(): Iterator<MyDate> = DateIterator(this)

    operator fun contains(d: MyDate): Boolean {
        return if (d < start || d > endInclusive)
            false
        else
            true
    }
}
