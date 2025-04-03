package com.example.elderlycarecoordination.ui.viewmodel

// Import AndroidX lifecycle components
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

// Import your UI model; ensure you have this class defined in your UI package.
import com.example.elderlycarecoordination.ui.screens.Medication

// Import Kotlin coroutines and Flow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

// Import Room annotations and classes
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * MedicationEntity represents how a Medication is stored in the Room database.
 */
@Entity(tableName = "medications")
data class MedicationEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val time: String,
    val foodInstruction: String,
    val explanation: String
)

/**
 * MedicationDao provides methods to interact with the medications table.
 */
@Dao
interface MedicationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMedication(entity: MedicationEntity)

    @Query("SELECT * FROM medications")
    fun getAllMedications(): Flow<List<MedicationEntity>>
}

/**
 * MedicationRepository defines the data operations for medications.
 */
interface MedicationRepository {
    val medications: Flow<List<MedicationEntity>>
    suspend fun insertMedication(entity: MedicationEntity)
}

/**
 * MedicationRepositoryImpl is a simple implementation of MedicationRepository using a MedicationDao.
 */
class MedicationRepositoryImpl(private val dao: MedicationDao) : MedicationRepository {
    override val medications: Flow<List<MedicationEntity>> = dao.getAllMedications()
    override suspend fun insertMedication(entity: MedicationEntity) {
        dao.insertMedication(entity)
    }
}

/**
 * MedicationTrackerViewModel exposes the list of medications (as a Flow of UI models)
 * and provides a function to add a new medication.
 */
class MedicationTrackerViewModel(
    private val repository: MedicationRepository
) : ViewModel() {

    // Convert the list of MedicationEntity from the database into the UI model (Medication)
    val medications: Flow<List<Medication>> = repository.medications.map { entityList ->
        entityList.map { it.toMedication() }
    }

    // Insert a new Medication into the database by converting it to a MedicationEntity
    fun addMedication(med: Medication) {
        viewModelScope.launch {
            repository.insertMedication(med.toMedicationEntity())
        }
    }
}

/**
 * Extension function to convert MedicationEntity (DB model) to Medication (UI model).
 */
private fun MedicationEntity.toMedication(): Medication {
    return Medication(
        name = name,
        time = time,
        foodInstruction = foodInstruction,
        explanation = explanation
    )
}

/**
 * Extension function to convert Medication (UI model) to MedicationEntity (DB model).
 */
private fun Medication.toMedicationEntity(): MedicationEntity {
    return MedicationEntity(
        name = name,
        time = time,
        foodInstruction = foodInstruction,
        explanation = explanation
    )
}
