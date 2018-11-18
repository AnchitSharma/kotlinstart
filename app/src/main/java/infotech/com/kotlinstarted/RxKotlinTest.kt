package infotech.com.kotlinstarted


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers


class RxKotlinTest : AppCompatActivity() {

    var compositeDisposable = CompositeDisposable()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rx_kotlin_test)
        compositeDisposable = CompositeDisposable()

        compositeDisposable.add(
                getNoteObservable()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .map {
                            it.note = it.note.toUpperCase()
                            it
                        }.subscribeWith(getNotesObserver()))


    }




    fun getNotesObserver():DisposableObserver<Note>{
        return object: DisposableObserver<Note>(){
            override fun onNext(t: Note) {
                println("Note: "+t.note)
            }

            override fun onError(e: Throwable) {
                println("onError: "+e.message)
            }

            override fun onComplete() {
                println("onComplete")
            }
        }
    }

    fun getNoteObservable():Observable<Note>{
        val list = prepareNotes()
        return Observable.create(object: ObservableOnSubscribe<Note>{
            override fun subscribe(emitter: ObservableEmitter<Note>) {
                list.forEach {
                    if (!emitter.isDisposed) emitter.onNext(it)
                }

                if (!emitter.isDisposed) emitter.onComplete()
            }

        })
    }
    fun prepareNotes():List<Note>{
        val notes = arrayListOf<Note>()
        notes.add(Note(1,"buy tooth paste!"))
        notes.add(Note(2,"do programming!"))
        notes.add(Note(3,"call brother!"))
        notes.add(Note(4,"watch narcos tonight!"))
        notes.add(Note(5,"pay power bill!"))
        return notes
    }




    data class Note(val id: Int, var note: String)

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable?.clear()
    }
}
