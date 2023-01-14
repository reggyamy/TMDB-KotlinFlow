package com.keyta.moviedatabase.utils

import com.keyta.moviedatabase.data.local.MovieEntity
import com.keyta.moviedatabase.domain.Movie


/**
 * Because in instrument testing just check sample data[0], data[1] - [19] left default
 */
object FakeData {

    fun getMovies(): List<Movie> {
        val movies = ArrayList<Movie>()
        for (i in 0..19)
        movies.add(
            Movie(
            id = 460465, //id
            title = "Mortal Kombat", //title
            backdropPath = "/9yBVqNruk6Ykrwc32qrK2TIE5xw.jpg", //backdrop
            posterPath = "/xGuOF1T3WmPsAcQEQJfnG7Ud9f8.jpg", //poster
            releaseDate =  "2021-04-07", //release date
            voteAverage = 7.7, // vote average
            overview = "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
            voteCount =  5083 ,
            popularity = 127.746)
        )
        return movies
    }
}