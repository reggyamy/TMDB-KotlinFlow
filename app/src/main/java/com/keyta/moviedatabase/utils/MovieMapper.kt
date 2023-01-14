package com.keyta.moviedatabase.utils

import com.keyta.moviedatabase.domain.Movie
import com.keyta.moviedatabase.data.local.MovieEntity
import com.keyta.moviedatabase.data.model.MovieResponse

object MovieMapper {

    fun mapResponseToEntities(input: List<MovieResponse>): List<MovieEntity> {
        val movie = ArrayList<MovieEntity>()
        input.map { response ->
            val movieEntity = MovieEntity(
                id = response.id,
                title = response.title,
                posterPath = response.posterPath,
                backdropPath = response.backdropPath,
                releaseDate = response.releaseDate,
                voteAverage = response.voteAverage,
                overview = response.overview,
                voteCount = response.voteCount,
                popularity = response.popularity
            )
            movie.add(movieEntity)
        }
        return movie
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> {
        val movie = ArrayList<Movie>()
        input.map { entity ->

//            val genre: MutableList<Genres> = mutableListOf()
//            entity.genreEntity?.forEach {
//                genre.add(Genres(id = it.id, name = it.name))
//            }
//            val genre = entity.genreEntity?.map {genreEntity ->
//                Genres(id = genreEntity.id, name = genreEntity.name)
//            }

//            val cast : MutableList<Person> = mutableListOf()
//            entity.cast.map{
//                Person(name = it.name, role = it.role, photoProfile = it.photoProfile)
//            }
//
//            val crew : MutableList<Person> = mutableListOf()
//            entity.crew.map {
//                Person(name = it.name, role = it.role, photoProfile = it.photoProfile)
//            }

//            val credit = arrayOf({cast}, {crew})

            val movieDomain = Movie(
                id = entity.id,
                title = entity.title,
                backdropPath = entity.backdropPath,
                posterPath = entity.posterPath,
                releaseDate = entity.releaseDate,
                voteAverage = entity.voteAverage,
                overview = entity.overview,
                voteCount = entity.voteCount,
                popularity = entity.popularity
//                genres = genre,
//                isFavorite = false,
//                cast = cast,
//                crew = crew
            )
            movie.add(movieDomain)
        }
        return movie
    }

//    fun mapDomainToEntity(input: Movie): MovieEntity {
////        val genre : MutableList<GenreEntity> = mutableListOf()
////        input.genres?.forEach {genres ->
////            genre.add(GenreEntity(name = genres.name, id = genres.id))
////        }
//
////        val cast : MutableList<PersonEntity> = mutableListOf()
////        input.cast.map{
////            PersonEntity(name = it.name, role = it.role, photoProfile = it.photoProfile)
////        }
////
////        val crew : MutableList<PersonEntity> = mutableListOf()
////        input.crew.map {
////            PersonEntity(name = it.name, role = it.role, photoProfile = it.photoProfile)
////        }
//
//        return MovieEntity(
//            id = input.id,
//            title = input.title,
//            backdropPath = input.backdropPath,
//            posterPath = input.posterPath,
//            releaseDate = input.releaseDate,
//            voteAverage = input.voteAverage,
//            overview = input.overview,
////            genreEntity = genre,
//            isFavorite = false,
////            cast = cast,
////            crew = crew
////            creditsEntity = CreditsEntity(cast, crew)
//        )
//    }

}