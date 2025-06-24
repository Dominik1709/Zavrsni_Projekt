import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Test
import com.example.mojtrening.data.VjezbaEntity

class FakeVjezbaRepo {
    private val vjezbe = mutableListOf<VjezbaEntity>()
    private val vjezbeFlow = MutableStateFlow<List<VjezbaEntity>>(emptyList())

    fun dodajVjezbu(vjezba: VjezbaEntity) {
        vjezbe.add(vjezba)
        vjezbeFlow.value = vjezbe
    }

    fun dohvatiSveVjezbe(): Flow<List<VjezbaEntity>> = vjezbeFlow

}

class TreningViewModelTest {

    @Test
    fun dodavanjeVjezbe_uFakeRepo_dodajeUListu() = runTest {
        val repo = FakeVjezbaRepo()
        val vjezba = VjezbaEntity(
            naziv = "Sklekovi",
            kilaza = "0",
            ponavljanja = "15",
            datum = "2025-06-24",
            treningId = 1
        )

        repo.dodajVjezbu(vjezba)

        val rezultat = repo.dohvatiSveVjezbe().first()
        assertTrue(rezultat.contains(vjezba))
    }
}
