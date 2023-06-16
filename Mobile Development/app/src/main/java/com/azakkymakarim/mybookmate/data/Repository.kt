package com.azakkymakarim.mybookmate.data

import com.azakkymakarim.mybookmate.model.DataSource
import com.azakkymakarim.mybookmate.model.ListBook
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class Repository {

    private val booksData = mutableListOf<ListBook>()

    init {
        if (booksData.isEmpty()) {
            DataSource.books.forEach {
                booksData.add(ListBook(it, false))
            }
        }
    }

    fun getAllBooks(): Flow<List<ListBook>> {
        return flowOf(booksData)
    }

    fun search(query: String): Flow<List<ListBook>> {
        return flowOf(
            booksData.filter {
                it.book.title.contains(query, ignoreCase = true)
            }
        )
    }

    fun getBookById(bookId: Long): ListBook {
        return booksData.first {
            it.book.id == bookId
        }
    }

    fun updateListBook(bookId: Long, newMark: Boolean): Flow<Boolean> {
        val index = booksData.indexOfFirst { it.book.id == bookId }
        val book = booksData[index]
        booksData[index] = book.copy(book = book.book, isBookmark = newMark)
        return flowOf(newMark)
    }

    fun getBookmarkBooks(): Flow<List<ListBook>> {
        return getAllBooks()
            .map { bookLists ->
                bookLists.filter { booklist ->
                    booklist.isBookmark
                }
            }
    }

    companion object {
        @Volatile
        private var instance: Repository? = null

        fun getInstance(): Repository =
            instance ?: synchronized(this) {
                instance ?: Repository()
            }.also { instance = it }
    }
}