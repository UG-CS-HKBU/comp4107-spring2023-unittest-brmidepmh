import junit.framework.TestCase.assertTrue
import org.junit.Test

class UnitTest {
    @Test
    fun test1() {
        heroes.clear()
        monarchHero = CaoCao()
        heroes.add(monarchHero)
        heroes.add(ZhangFei(MinisterRole()))
        assertTrue(monarchHero.name == "Cao Cao")
    }

    @Test
    fun test2() {
        if (heroes.size < 2) {
            test1()
            assertTrue(heroes.size == 2)
        }
    }
}