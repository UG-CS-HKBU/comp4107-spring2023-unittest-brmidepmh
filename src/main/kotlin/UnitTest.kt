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

    @Test
    fun testCaoDodgeAttack() {
        monarchHero = CaoCao()
        for (i in 1..7) {
            heroes.add(NoneMonarchFactory.createRandomHero())
        }
        assertTrue(monarchHero.dodgeAttack())
    }

    @Test
    fun testBeingAttacked() {
        for (i in 1..7) {
            heroes.add(NoneMonarchFactory.createRandomHero())
        }
        for (hero in heroes) {
            val spy = object : Hero(NoneMonarchFactory.getRandomRole()) {
                override val name = hero.name
                override fun beingAttacked() {
                    hero.beingAttacked()
                    assertTrue(hero.hp >= 0)
                }
            }
            for(i in 1..10)
                spy.beingAttacked()
        }
    }

    object FakeNonmonarchFactory : GameObjectFactory{
        var count = 0
        var last: WeiHero? = null
        init {
            monarchHero = CaoCao()
        }
        override fun getRandomRole(): Role {
            return MinisterRole()
        }
        override fun createRandomHero(): Hero {
            val hero = when(count++) {
                0->SimaYi(getRandomRole())
                1->XiaHouyuan(getRandomRole())
                else->XuChu(getRandomRole())
            }
            val cao = monarchHero as CaoCao
            if (last == null)
                cao.helper = hero
            else
                last!!.setNext(hero)
            last = hero
            return hero
        }
    }

    object FakeMonarchFactory : GameObjectFactory{
        override fun createRandomHero() : Hero {
            return CaoCao()
        }
        override fun getRandomRole(): Role {
            return MonarchRole()
        }
    }

    class CaoCaoUnitTest {
        @Test
        fun testCaoDodgeAttack(){
            heroes.add(FakeMonarchFactory.createRandomHero())
            for (i in 0..2) {
                var hero = FakeNonmonarchFactory.createRandomHero()
                hero.index = heroes.size;
                heroes.add(hero)
            }
            for (hero in heroes) {
                hero.beingAttacked()
                hero.templateMethod()
            }
//            assert(heroes[0].dodgeAttack())

        }
    }
}