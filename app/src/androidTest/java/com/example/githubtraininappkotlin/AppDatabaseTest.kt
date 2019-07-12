package com.example.githubtraininappkotlin


import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.githubtraininappkotlin.database.AppDatabase
import com.example.githubtraininappkotlin.database.OwnerDao
import com.example.githubtraininappkotlin.models.OwnerEntity
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.lang.Exception

@RunWith(AndroidJUnit4::class)
class AppDatabaseTest{
    private lateinit var ownerDao: OwnerDao
    private lateinit var db: AppDatabase

    @Before
fun createDb(){
val context =InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        ownerDao = db.ownerDatabaseDao
    }

@After
@Throws(IOException::class)
fun closeDb(){
db.close()
}

@Test
@Throws(Exception::class)
fun insertAndGetOwner(){
    val owner = OwnerEntity(0,"LaviniaDragunoi", 1, "Bucuresti", "lavinia.dragunoi@yahoo.ro",
        "avatarString", "urlString", "followersUrl","kkk","vvvv","vvvv",
        "ds","ds","asas","ds","fdas", "sfdsfa", "dfgasg",
    true,"dsgf","dgsdfg","erfgds","dfagadg","agdfga",true,
        "adfg",4,4,45,5,"sgsdf","dgfsg",5,
        6,7,8,9,true)
    ownerDao.insertOwner(owner)
    val getOwner = ownerDao.getOwner()
    Assert.assertEquals(getOwner?.login, "LaviniaDragunoi")
}
}