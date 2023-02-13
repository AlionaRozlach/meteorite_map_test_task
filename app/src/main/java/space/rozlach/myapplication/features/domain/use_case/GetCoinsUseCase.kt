package space.rozlach.myapplication.features.domain.use_case

import kotlinx.coroutines.flow.Flow
import space.rozlach.myapplication.recourse.Resource
import space.rozlach.myapplication.features.domain.model.Meteorite
import space.rozlach.myapplication.features.domain.repository.MeteoriteRepository

class GetCoinsUseCase (
    private val repository: MeteoriteRepository
){
    suspend operator fun invoke(): Flow<Resource<List<Meteorite>>>
    {
        return repository.getMeteorites()

    }
}