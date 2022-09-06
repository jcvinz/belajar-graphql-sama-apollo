package id.kotlin.rickandmortyproject

//class ViewModelFactory private constructor(private val dataRepository: DataRepository) :
//    ViewModelProvider.NewInstanceFactory() {
//
//    @Suppress("UNCHEKED_CAST")
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        return when {
//            modelClass.isAssignableFrom(MainViewModel::class.java) -> MainViewModel(dataRepository) as T
//            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
//        }
//    }
//
//    companion object {
//        @Volatile
//        private var instance: ViewModelFactory? = null
//        fun getInstance(): ViewModelFactory = instance ?: synchronized(this) {
//            instance ?: ViewModelFactory(Injection.provideRepository())
//        }.also { instance = it }
//
//    }
//}