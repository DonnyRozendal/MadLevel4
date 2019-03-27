package nl.hva.madlevel4.core.di

import androidx.room.Room
import nl.hva.madlevel4.features.data.repositories.BucketListRepository
import nl.hva.madlevel4.features.data.room.BucketListDatabase
import nl.hva.madlevel4.features.domain.ClearBucketListUseCase
import nl.hva.madlevel4.features.domain.DeleteBucketListItemUseCase
import nl.hva.madlevel4.features.domain.GetBucketListUseCase
import nl.hva.madlevel4.features.domain.InsertBucketListItemUseCase
import nl.hva.madlevel4.features.presentation.additem.AddItemViewModel
import nl.hva.madlevel4.features.presentation.bucketlist.BucketListViewModel
import org.koin.androidx.viewmodel.experimental.builder.viewModel
import org.koin.dsl.module.module
import org.koin.experimental.builder.factory

val applicationModule = module {
    viewModel<BucketListViewModel>()
    viewModel<AddItemViewModel>()
}

val useCaseModule = module {
    factory<GetBucketListUseCase>()
    factory<InsertBucketListItemUseCase>()
    factory<DeleteBucketListItemUseCase>()
    factory<ClearBucketListUseCase>()
}

val repositoryModule = module {
    single<BucketListRepository> { BucketListRepository.Network(get()) }
}

val roomModule = module {
    single { Room.databaseBuilder(get(), BucketListDatabase::class.java, "bucket-list-db").build() }
    single { get<BucketListDatabase>().bucketListDao() }
}

val koinModules = listOf(
        applicationModule,
        useCaseModule,
        repositoryModule,
        roomModule
)