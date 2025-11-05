package od.util
import kotlin.random.Random

object Dice {
    fun d(sides:Int) = Random.nextInt(1, sides + 1)
    fun roll(count:Int, sides:Int) = (1..count).sumOf { d(sides) }
    fun roll3d6() = roll(3, 6)
    fun roll4d6DropLowest(): Int {
        val r = IntArray(4) { d(6) }.sortedDescending()
        return r.take(3).sum()
    }
}
