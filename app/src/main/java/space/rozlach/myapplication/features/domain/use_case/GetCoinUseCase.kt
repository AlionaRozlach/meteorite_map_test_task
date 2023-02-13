package space.rozlach.myapplication.features.domain.use_case

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import space.rozlach.myapplication.recourse.Resource
import space.rozlach.myapplication.features.domain.model.Meteorite
import space.rozlach.myapplication.features.domain.repository.MeteoriteRepository

class GetCoinUseCase (
    private val repository: MeteoriteRepository
){
    suspend operator fun invoke(name: String): Flow<Resource<Meteorite>>
    {
        if(name.isBlank())
        {
            return flow {  }
        }
        return repository.getMeteoriteInfo(name)

    }
}