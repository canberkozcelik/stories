package com.canberkozcelik.cstory.model

import android.os.Parcelable
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


/**
 * Created by canberkozcelik on 2019-05-12.
 */
@Parcelize
data class DummyResponse(@SerializedName("stories") val stories: List<Story>) : Parcelable {
    companion object {
        fun dummy(): DummyResponse {
            return Gson().fromJson<Any>(
                "{\n" +
                        "    \"stories\":[\n" +
                        "        {\n" +
                        "            \"id\":\"a1\",\n" +
                        "            \"title\":\"Yemek sepeti indirimi asdasdas as das das das das desssss\",\n" +
                        "            \"icon\":\"https://r1.ilikewallpaper.net/iphone-8-wallpapers/download/35756/Sunset-Nature-Mountain-Wood-Red-Sky-Lake-iphone-8-wallpaper-ilikewallpaper_com.jpg\",\n" +
                        "            \"image\":\"https://r1.ilikewallpaper.net/iphone-8-wallpapers/download/35756/Sunset-Nature-Mountain-Wood-Red-Sky-Lake-iphone-8-wallpaper-ilikewallpaper_com.jpg\",\n" +
                        "            \"btnTitle\":\"Kampanyaya katıl\",\n" +
                        "            \"btnLink\":\"https://www.google.com\"\n" +
                        "        },\n" +
                        "        {\n" +
                        "            \"id\":\"b1\",\n" +
                        "            \"title\":\"Yemek sepeti indirimi sakin kacirma\",\n" +
                        "            \"icon\":\"https://i.pinimg.com/736x/a1/d4/ea/a1d4ea964c62603ec20acd16eb0e5c85.jpg\",\n" +
                        "            \"image\":\"https://i.pinimg.com/736x/a1/d4/ea/a1d4ea964c62603ec20acd16eb0e5c85.jpg\",\n" +
                        "            \"btnTitle\":\"Kampanyaya katıl 2\",\n" +
                        "            \"btnLink\":\"https://www.bjk.com.tr\"\n" +
                        "        },\n" +
                        "        {\n" +
                        "            \"id\":\"d1\",\n" +
                        "            \"title\":\"Yemek sepeti\",\n" +
                        "            \"icon\":\"https://static1.squarespace.com/static/5397b451e4b07f25d1682866/5643b980e4b0eaf052b84a23/5643b983e4b00c09aadce12d/1447278987246/Apple-iPhone-5-5s-Wallpaper-Good-Dino-3.jpg\",\n" +
                        "            \"image\":\"https://static1.squarespace.com/static/5397b451e4b07f25d1682866/5643b980e4b0eaf052b84a23/5643b983e4b00c09aadce12d/1447278987246/Apple-iPhone-5-5s-Wallpaper-Good-Dino-3.jpg\",\n" +
                        "            \"btnTitle\":\"Kampanyaya katılma Hurriyet\",\n" +
                        "            \"btnLink\":\"https://www.hurriyet.com.tr\"\n" +
                        "        },\n" +
                        "        {\n" +
                        "            \"id\":\"e1\",\n" +
                        "            \"title\":\"Yemek sepeti avantajli menuler\",\n" +
                        "            \"icon\":\"https://i.pinimg.com/736x/62/32/ef/6232ef50dd5b94cb97960e22c7c6dafb--galaxy-wallpaper-iphone-iphone-.jpg\",\n" +
                        "            \"image\":\"https://i.pinimg.com/736x/62/32/ef/6232ef50dd5b94cb97960e22c7c6dafb--galaxy-wallpaper-iphone-iphone-.jpg\",\n" +
                        "            \"btnTitle\":\"Kampanyaya katılma 2\",\n" +
                        "            \"btnLink\":\"vftr.co/ETHBSKS\"\n" +
                        "        },\n" +
                        "        {\n" +
                        "            \"id\":\"f1\",\n" +
                        "            \"title\":\"Avantajli Menuler\",\n" +
                        "            \"icon\":\"https://static1.squarespace.com/static/5397b451e4b07f25d1682866/5643bc81e4b021eb442c7b72/5643bddee4b07810c0b2f749/1447280129681/Samsung-Galaxy-S5-Android-Wallpaper-Good-Dino-2.jpg\",\n" +
                        "            \"image\":\"https://static1.squarespace.com/static/5397b451e4b07f25d1682866/5643bc81e4b021eb442c7b72/5643bddee4b07810c0b2f749/1447280129681/Samsung-Galaxy-S5-Android-Wallpaper-Good-Dino-2.jpg\",\n" +
                        "            \"btnTitle\":\"Kampanyaya katılma 3\",\n" +
                        "            \"btnLink\":\"vftr.co/ETHBSKS\"\n" +
                        "        },\n" +
                        "        {\n" +
                        "            \"id\":\"g1\",\n" +
                        "            \"title\":\"Avantajli Menuler 2\",\n" +
                        "            \"icon\":\"https://images.bluethumb.com.au/uploads/listing/118572/brita-lee-tiger-in-bamboo-forest-bluethumb-647f.jpg?w=800&auto=compress&cs=tinysrgb&q=70&s=b50eb51ef394a44b0d38e6fbea955159\",\n" +
                        "            \"image\":\"https://images.bluethumb.com.au/uploads/listing/118572/brita-lee-tiger-in-bamboo-forest-bluethumb-647f.jpg?w=800&auto=compress&cs=tinysrgb&q=70&s=b50eb51ef394a44b0d38e6fbea955159\",\n" +
                        "            \"btnTitle\":\"Kampanyaya katılma 4\",\n" +
                        "            \"btnLink\":\"vftr.co/ETHBSKS\"\n" +
                        "        },\n" +
                        "        {\n" +
                        "            \"id\":\"h1\",\n" +
                        "            \"title\":\"Avantajli Secenekler\",\n" +
                        "            \"icon\":\"http://www.appleplans.co.uk/wp-content/uploads/2017/03/If-it-looks-good-and-all.jpg\",\n" +
                        "            \"image\":\"http://www.appleplans.co.uk/wp-content/uploads/2017/03/If-it-looks-good-and-all.jpg\",\n" +
                        "            \"btnTitle\":\"Kampanya\",\n" +
                        "            \"btnLink\":\"vftr.co/ETHBSKS\"\n" +
                        "        },\n" +
                        "        {\n" +
                        "            \"id\":\"i1\",\n" +
                        "            \"title\":\"Avantaj\",\n" +
                        "            \"icon\":\"https://theultralinx.com/.image/t_share/MTQxOTUxNzg4ODkwNDAwNDUy/212iphoneiphone-wallpaperswallpapers1jpg.jpg\",\n" +
                        "            \"image\":\"https://theultralinx.com/.image/t_share/MTQxOTUxNzg4ODkwNDAwNDUy/212iphoneiphone-wallpaperswallpapers1jpg.jpg\",\n" +
                        "            \"btnTitle\":\"Kampanya\",\n" +
                        "            \"btnLink\":\"vftr.co/ETHBSKS\"\n" +
                        "        },\n" +
                        "        {\n" +
                        "            \"id\":\"j1\",\n" +
                        "            \"title\":\"Firsatlari gor\",\n" +
                        "            \"icon\":\"https://hdqwalls.com/download/8-bit-pixel-art-city-2o-1280x2120.jpg\",\n" +
                        "            \"image\":\"https://hdqwalls.com/download/8-bit-pixel-art-city-2o-1280x2120.jpg\",\n" +
                        "            \"btnTitle\":\"Detaylar\",\n" +
                        "            \"btnLink\":\"vftr.co/ETHBSKS\"\n" +
                        "        },\n" +
                        "        {\n" +
                        "            \"id\":\"k1\",\n" +
                        "            \"title\":\"Firsatlari gor 2\",\n" +
                        "            \"icon\":\"https://i-cdn.phonearena.com/images/articles/257597-image/boom.jpg\",\n" +
                        "            \"image\":\"https://i-cdn.phonearena.com/images/articles/257597-image/boom.jpg\",\n" +
                        "            \"gif\":\"https://www.vodafone.com.tr/ymkspt.gif\",\n" +
                        "            \"btnTitle\":\"Detaylari gor\",\n" +
                        "            \"btnLink\":\"vftr.co/ETHBSKS\"\n" +
                        "        },\n" +
                        "        {\n" +
                        "            \"id\":\"l1\",\n" +
                        "            \"title\":\"Firsatlari gor 3\",\n" +
                        "            \"icon\":\"http://www.designbolts.com/wp-content/uploads/2017/04/paris-iPhone-7-Wallpaper-HD.jpg\",\n" +
                        "            \"image\":\"http://www.designbolts.com/wp-content/uploads/2017/04/paris-iPhone-7-Wallpaper-HD.jpg\",\n" +
                        "            \"btnTitle\":\"\",\n" +
                        "            \"btnLink\":\"vftr.co/ETHBSKS\"\n" +
                        "        },\n" +
                        "        {\n" +
                        "            \"id\":\"m1\",\n" +
                        "            \"title\":\"Firsatlari gor 4\",\n" +
                        "            \"icon\":\"https://upload.wikimedia.org/wikipedia/commons/f/f5/Steve_Jobs_Headshot_2010-CROP2.jpg\",\n" +
                        "            \"image\":\"https://upload.wikimedia.org/wikipedia/commons/f/f5/Steve_Jobs_Headshot_2010-CROP2.jpg\",\n" +
                        "            \"btnTitle\":\"\",\n" +
                        "            \"btnLink\":\"vftr.co/ETHBSKS\"\n" +
                        "        },\n" +
                        "        {\n" +
                        "            \"id\":\"n1\",\n" +
                        "            \"title\":\"Aradigin firsatlar burada\",\n" +
                        "            \"icon\":\"https://www.droidviews.com/wp-content/uploads/2016/09/iOS10_wall_droidviews_021.jpg\",\n" +
                        "            \"image\":\"https://www.droidviews.com/wp-content/uploads/2016/09/iOS10_wall_droidviews_021.jpg\",\n" +
                        "            \"btnTitle\":\"Incele\",\n" +
                        "            \"btnLink\":\"vftr.co/ETHBSKS\"\n" +
                        "        },\n" +
                        "        {\n" +
                        "            \"id\":\"o1\",\n" +
                        "            \"title\":\"Firsatlari hemen incele\",\n" +
                        "            \"icon\":\"https://boygeniusreport.files.wordpress.com/2016/11/papers-co-ni07-night-drive-car-light-red-dark-34-iphone6-plus-wallpaper.jpg\",\n" +
                        "            \"image\":\"https://boygeniusreport.files.wordpress.com/2016/11/papers-co-ni07-night-drive-car-light-red-dark-34-iphone6-plus-wallpaper.jpg\",\n" +
                        "            \"btnTitle\":\"\",\n" +
                        "            \"btnLink\":\"vftr.co/ETHBSKS\"\n" +
                        "        }\n" +
                        "    ]\n" +
                        "}", DummyResponse::class.java
            ) as DummyResponse
        }
    }
}