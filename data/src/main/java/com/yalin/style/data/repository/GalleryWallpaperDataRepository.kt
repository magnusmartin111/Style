package com.yalin.style.data.repository

import com.yalin.style.data.entity.mapper.WallpaperEntityMapper
import com.yalin.style.data.repository.datasource.GalleryWallpaperDataStoreFactory
import com.yalin.style.domain.AdvanceWallpaper
import com.yalin.style.domain.GalleryWallpaper
import com.yalin.style.domain.Wallpaper
import com.yalin.style.domain.repository.WallpaperRepository
import io.reactivex.Observable
import java.io.InputStream
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author jinyalin
 * @since 2017/5/24.
 */
@Singleton
class GalleryWallpaperDataRepository @Inject
constructor(val galleryWallpaperDataStoreFactory: GalleryWallpaperDataStoreFactory,
            val wallpaperEntityMapper: WallpaperEntityMapper) :
        WallpaperRepository {

    override fun getWallpaper(): Observable<Wallpaper> =
            galleryWallpaperDataStoreFactory.create()
                    .wallPaperEntity.map(wallpaperEntityMapper::transform)

    override fun switchWallpaper(): Observable<Wallpaper> =
            galleryWallpaperDataStoreFactory.create()
                    .switchWallPaperEntity().map(wallpaperEntityMapper::transform)

    override fun openInputStream(wallpaperId: String?): Observable<InputStream> =
            galleryWallpaperDataStoreFactory.create()
                    .openInputStream(wallpaperId)

    override fun getWallpaperCount(): Observable<Int> =
            galleryWallpaperDataStoreFactory.create()
                    .wallpaperCount

    override fun likeWallpaper(wallpaperId: String): Observable<Boolean> =
            galleryWallpaperDataStoreFactory.create()
                    .likeWallpaper(wallpaperId)

    override fun addGalleryWallpaperUris(uris: List<GalleryWallpaper>): Observable<Boolean> =
            galleryWallpaperDataStoreFactory.create()
                    .addGalleryWallpaperUris(uris)

    override fun removeGalleryWallpaperUris(uris: List<GalleryWallpaper>): Observable<Boolean> =
            galleryWallpaperDataStoreFactory.create()
                    .removeGalleryWallpaperUris(uris)

    override fun getGalleryWallpapers(): Observable<List<GalleryWallpaper>> =
            galleryWallpaperDataStoreFactory.create()
                    .getGalleryWallpaperUris().map(wallpaperEntityMapper::transformGalleryWallpaper)

    override fun getAdvanceWallpapers(): Observable<List<AdvanceWallpaper>> {
        return Observable.create<List<AdvanceWallpaper>> { emitter ->
            emitter.onError(IllegalStateException(
                    "GalleryWallpaperDataRepository have not advance wallpapers."))
        }
    }

    override fun loadAdvanceWallpapers(): Observable<List<AdvanceWallpaper>> {
        return Observable.create<List<AdvanceWallpaper>> { emitter ->
            emitter.onError(IllegalStateException(
                    "GalleryWallpaperDataRepository cannot load advance wallpapers."))
        }
    }

    override fun downloadAdvanceWallpaper(wallpaperId: String): Observable<Long> {
        return Observable.create<Long> { emitter ->
            emitter.onError(IllegalStateException(
                    "GalleryWallpaperDataRepository cannot download advance wallpapers."))
        }
    }

    override fun selectAdvanceWallpaper(wallpaperId: String, tempSelect: Boolean):
            Observable<Boolean> {
        return Observable.create<Boolean> { emitter ->
            emitter.onError(IllegalStateException(
                    "GalleryWallpaperDataRepository cannot select advance wallpapers."))
        }
    }

    override fun getAdvanceWallpaper(): AdvanceWallpaper {
        throw IllegalStateException(
                "GalleryWallpaperDataRepository cannot get advance wallpapers.")
    }

    override fun readAdvanceAd(wallpaperId: String?): Observable<Boolean> {
        throw IllegalStateException(
                "GalleryWallpaperDataRepository cannot read advance ad.")
    }

    override fun foreNow(wallpaperUri: String): Observable<Boolean> =
            galleryWallpaperDataStoreFactory.create().forceNow(wallpaperUri)

    override fun setGalleryUpdateInterval(intervalMin: Int): Observable<Boolean> =
            galleryWallpaperDataStoreFactory.create().setUpdateIntervalMin(intervalMin)

    override fun getGalleryUpdateInterval(): Observable<Int> =
            galleryWallpaperDataStoreFactory.create().getUpdateIntervalMin()
}