package adeel.moviedb.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


class DateUtils{

    companion object {
        fun getDayOfWeek(date: Date): String {
            val calen = Calendar.getInstance()
            calen.time = date
            return calen.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault())
        }

        fun isDateGreaterThanToday(date: String):Boolean{
            val sdate = SimpleDateFormat("yyyy-MM-dd")
            val strDate = sdate.parse(date)

            return System.currentTimeMillis() > strDate.time
        }

        fun getStringDate(date: String): String {

            val dateArray = date.split("-".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val sdate = SimpleDateFormat("yyyy-MM-dd")

            try {
                val mDate = sdate.parse(date)
                val timeInMilliseconds = mDate.time
                val dateString = formatDate(timeInMilliseconds).split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                return dateString[0].substring(0,3) + " " + dateString[1] + ", " + dateArray[0]
            } 
            
            catch (e: ParseException) {
                
                e.printStackTrace()
            }

            return ""
        }

        fun getDateFromEpoch(epoch: Long):String{
            val sdate = SimpleDateFormat("yyyy-MM-dd")
            return sdate.format(Date(epoch))
        }


        fun formatTime(timeInMillis: Long): String {
            val df = SimpleDateFormat("HH:mm", Locale.getDefault())

            return df.format(timeInMillis)
        }

        fun formatTimeWithMarker(timeInMillis: Long): String {
            val df = SimpleDateFormat("h:mm a", Locale.getDefault())
            return df.format(timeInMillis)
        }

        fun getHourOfDay(timeInMillis: Long): Int {
            val df = SimpleDateFormat("H", Locale.getDefault())
            return Integer.valueOf(df.format(timeInMillis))!!
        }

        fun getMinute(timeInMillis: Long): Int {
            val df = SimpleDateFormat("m", Locale.getDefault())
            return Integer.valueOf(df.format(timeInMillis))!!
        }

        fun getYear(timeInMillis: Long): String {
            val df = SimpleDateFormat("YYYY", Locale.getDefault())
            return df.format(timeInMillis)!!
        }

        fun formatDateTime(timeInMillis: Long): String {
            return if (isToday(timeInMillis)) {
                formatTime(timeInMillis)
            } else if (isYesterday(timeInMillis)) {
                "Yesterday"
            } else {
                formatDate(timeInMillis)
            }
        }

        
        fun formatDate(timeInMillis: Long): String {
            val df = SimpleDateFormat("MMMM dd", Locale.getDefault())
            return df.format(timeInMillis)
        }

        
        fun isToday(timeInMillis: Long): Boolean {
            val df = SimpleDateFormat("yyyyMMdd", Locale.getDefault())
            val dt = df.format(timeInMillis)
            return dt == df.format(System.currentTimeMillis())
        }

        fun isYesterday(timeInMillis: Long): Boolean {
            val df = SimpleDateFormat("yyyyMMdd", Locale.getDefault())
            val dt = df.format(timeInMillis)
            return dt == df.format(System.currentTimeMillis() - 86400000)
        }

        fun hasSameDate(millisFirst: Long, millisSecond: Long): Boolean {
            val df = SimpleDateFormat("yyyyMMdd", Locale.getDefault())
            return df.format(millisFirst) == df.format(millisSecond)
        }
    }

}