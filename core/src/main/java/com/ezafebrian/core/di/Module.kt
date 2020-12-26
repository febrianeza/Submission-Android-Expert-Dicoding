package com.ezafebrian.core.di

import androidx.room.Room
import com.ezafebrian.core.data.FeedsRepository
import com.ezafebrian.core.data.source.local.LocalDataSource
import com.ezafebrian.core.data.source.local.database.Database
import com.ezafebrian.core.data.source.remote.RemoteDataSource
import com.ezafebrian.core.data.source.remote.network.ApiService
import com.ezafebrian.core.domain.repository.IFeedsRepository
import com.ezafebrian.core.utils.AppExecutors
import com.ezafebrian.core.utils.Consts.BASE_URL
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory {
        get<Database>().dao()
    }
    single {
        val passPhrase: ByteArray = SQLiteDatabase.getBytes("dicoding".toCharArray())
        val factory = SupportFactory(passPhrase)

        Room.databaseBuilder(
            androidContext(),
            Database::class.java,
            "Feed.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val networkModule = module {
    single {
        val hostname = "dummyapi.io"

        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/a4FoHyEnsFhauIx0w/gB7ywslD6tWGk83J2F6Pv1phA=")
            .build()

        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }

    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()

        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IFeedsRepository> { FeedsRepository(get(), get(), get()) }
}