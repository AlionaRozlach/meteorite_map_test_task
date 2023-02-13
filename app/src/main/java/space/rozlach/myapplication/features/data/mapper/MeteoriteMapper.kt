package space.rozlach.myapplication.features.data.mapper

import space.rozlach.myapplication.features.data.local.entity.MeteoriteEntity
import space.rozlach.myapplication.features.domain.model.Meteorite

fun MeteoriteEntity.toMeteorite(): Meteorite {
    return Meteorite(
        name = name,
        nameType = nameType,
        mass = mass,
        fall = fall,
        year = year,
        recLat = recLat,
        recLong = recLong
    )
}

fun Meteorite.toEntity(): MeteoriteEntity{
    return MeteoriteEntity(
        name = name,
        nameType = nameType,
        mass = mass,
        fall = fall,
        year = year,
        recLat = recLat,
        recLong = recLong
    )
}