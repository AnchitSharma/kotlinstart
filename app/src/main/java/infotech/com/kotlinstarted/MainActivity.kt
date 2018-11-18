package infotech.com.kotlinstarted

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.UI
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {
    lateinit var listview : ListView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //exampleBlocking()
        exampleLaunchCoroutineScope()
    }



    suspend fun printDelayed(message:String){
        delay(1000)
        println(message)
    }

    fun exampleBlocking()= runBlocking{
        println("ONE")
        printDelayed("TWO")
        println("THREE")
    }


    fun exampleBlockingDispatcher(){
        runBlocking(Dispatchers.Default) {
            println("one - from thread ${Thread.currentThread().name }")
            printDelayed("two - from thread ${Thread.currentThread().name }")
        }
        println("three - from thread ${Thread.currentThread().name}")
    }

    fun exampleLaunchingGlobal() = runBlocking {
        println("one - from thread ${Thread.currentThread().name }")
        GlobalScope.launch {
            printDelayed("two - from thread ${Thread.currentThread().name }")//NON BLOCKING
        }
        println("one - from thread ${Thread.currentThread().name }")
        delay(3000)
    }

    fun exampleLaunchingGlobalWaiting() = runBlocking {
        println("one - from thread ${Thread.currentThread().name }")
        val job = GlobalScope.launch {
            printDelayed("two - from thread ${Thread.currentThread().name }")//NON BLOCKING
        }
        println("one - from thread ${Thread.currentThread().name }")
        job.join()
    }

    fun exampleLaunchCoroutineScope() = runBlocking {
        println("one - from thread ${Thread.currentThread().name }")
        //launch is non blocking. i have find a solution for a problem if user go to search in case launch is pushing data in database.
        //
        //creating our known dispatcher
        val customDispatcher = Executors.newFixedThreadPool(2).asCoroutineDispatcher()
        launch {
            printDelayed("two - from thread ${Thread.currentThread().name }")//NON BLOCKING
        }
        launch(Dispatchers.IO) {
            printDelayed("two - from thread ${Thread.currentThread().name }")//NON BLOCKING
        }
        launch(customDispatcher) {
            printDelayed("two - from thread ${Thread.currentThread().name }")//NON BLOCKING
        }

        (customDispatcher.executor as ExecutorService).shutdown()
        //because of Android we can use

        println("one - from thread ${Thread.currentThread().name }")

    }

    suspend fun calculateHardThings(int:Int):Int{
        delay(1000);
        return (int*10)
    }

    fun exampleAsyncAwait() = runBlocking {
        val startTime = System.currentTimeMillis()
        val deferred1 = async { calculateHardThings(10) }
        val deferred2 = async { calculateHardThings(10) }
        val deferred3 = async { calculateHardThings(10) }

        val sum = deferred1.await()+deferred2.await()+deferred3.await()
        println("async/await result = $sum")

        val endTime = System.currentTimeMillis()
        println("Execution Time: "+(endTime-startTime))
    }












}
