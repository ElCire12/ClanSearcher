//package com.example.apilistapp.data.repository
//
//import androidx.paging.PagingSource
//import androidx.paging.PagingState
//import com.example.apilistapp.data.mapper.toDomain
//import com.example.apilistapp.domain.ClanDomain
//
//class ClanPagingSource(
//    private val repository: ApiRepository,
//    private val query: String? = null
//) : PagingSource<String, ClanDomain>() {
//
//    override fun getRefreshKey(state: PagingState<String, ClanDomain>): String? {
//        // En paginación por cursor, lo más sencillo es refrescar desde el principio
//        return null
//    }
//
//    override suspend fun load(params: LoadParams<String>): LoadResult<String, ClanDomain> {
//        return try {
//            // 1. Obtenemos el cursor actual (null la primera vez)
//            val currentCursor = params.key
//
//            // 2. Llamamos a la API a través del interface del repositorio
//            // Nota: Asegúrate de haber añadido el parámetro 'after' en tu ApiInterface
//            val response = repository.apiInterface.getClansList(
//                name = query,
//                after = currentCursor,
//                limit = params.loadSize
//            )
//
//            if (response.isSuccessful) {
//                val body = response.body()
//                val clans = body?.clans?.map { it.toDomain() } ?: emptyList()
//
//                // 3. Extraemos el siguiente cursor de la respuesta de la API
//                val nextAfterCursor = body?.paging?.cursors?.after
//
//                LoadResult.Page(
//                    data = clans,
//                    prevKey = null, // La API de CoC normalmente no facilita ir hacia atrás fácilmente
//                    nextKey = if (clans.isEmpty() || nextAfterCursor == null) null else nextAfterCursor
//                )
//            } else {
//                LoadResult.Error(Exception("Error en la API: ${response.code()}"))
//            }
//        } catch (e: Exception) {
//            // Gestionamos errores de red o conversión
//            LoadResult.Error(e)
//        }
//    }
//}