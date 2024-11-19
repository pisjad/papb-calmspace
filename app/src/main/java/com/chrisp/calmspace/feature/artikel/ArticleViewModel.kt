// File: app/src/main/java/com/chrisp/calmspace/feature/artikel/ArticleViewModel.kt

package com.chrisp.calmspace.feature.artikel

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.chrisp.calmspace.R
import com.chrisp.calmspace.model.ArticleModel

class ArticleViewModel : ViewModel() {
    private val _selectedTab = mutableStateOf(0)
    val selectedTab: State<Int> = _selectedTab

    private val _searchQuery = mutableStateOf("")
    val searchQuery: State<String> = _searchQuery

    private val _articles = listOf(
        ArticleModel(
            id = 1,
            image = R.drawable.img_ocpd,
            title = "Mengenal Obsessive-Compulsive Personality Disorder (OCPD)",
            content = """
                OCPD adalah gangguan kepribadian yang ditandai dengan preokupasi berlebihan terhadap keteraturan, perfeksionisme, dan kontrol mental/interpersonal.
                
                Gejala OCPD meliputi:
                - Perfeksionisme yang mengganggu penyelesaian tugas
                - Preokupasi berlebihan dengan detail, aturan, dan daftar
                - Dedikasi berlebihan terhadap pekerjaan yang mengorbankan waktu luang
                - Infleksibilitas tentang moral dan etika
                - Ketidakmampuan untuk membuang barang usang atau tidak berharga
                - Keengganan untuk mendelegasikan tugas
                - Gaya pengeluaran yang kaku dengan uang
                - Kekakuan dan keras kepala
                
                Penting untuk diingat bahwa OCPD berbeda dengan OCD (Obsessive-Compulsive Disorder). Sementara OCD ditandai dengan obsesi yang tidak diinginkan dan perilaku kompulsif, OCPD melibatkan pola pikir dan perilaku yang dianggap benar dan diinginkan oleh individu tersebut.
                
                Jika Anda merasa memiliki gejala OCPD, konsultasikan dengan profesional kesehatan mental untuk diagnosis dan penanganan yang tepat.
            """.trimIndent(),
            category = "Artikel",
            date = "2024-03-01",
            author = "Dr. Sarah Johnson"
        ),
        ArticleModel(
            id = 2,
            image = R.drawable.img_anxiety,
            title = "Mengenal Anxiety",
            content = """
                Anxiety atau kecemasan adalah respons alami tubuh terhadap stres, namun dapat menjadi masalah ketika terjadi secara berlebihan.
                
                Gejala umum anxiety meliputi:
                - Perasaan gelisah atau tegang
                - Detak jantung yang cepat
                - Berkeringat
                - Tremor atau gemetar
                - Kesulitan berkonsentrasi
                - Gangguan tidur
                - Kelelahan
                - Kekhawatiran yang berlebihan
                
                Anxiety dapat dikelola melalui berbagai cara:
                1. Terapi profesional
                2. Teknik relaksasi dan pernapasan
                3. Olahraga teratur
                4. Pola tidur yang sehat
                5. Membatasi kafein dan alkohol
                6. Meditasi atau mindfulness
                
                Jika anxiety mengganggu kehidupan sehari-hari Anda, jangan ragu untuk mencari bantuan profesional.
            """.trimIndent(),
            category = "Artikel",
            date = "2024-03-05",
            author = "Dr. Michael Chen"
        ),
        ArticleModel(
            id = 3,
            image = R.drawable.img_depression,
            title = "Mengenal Depresi",
            content = """
                Depresi adalah gangguan suasana hati yang menyebabkan perasaan sedih berkepanjangan dan hilangnya minat dalam aktivitas sehari-hari.
                
                Gejala utama depresi meliputi:
                - Perasaan sedih atau kosong yang persisten
                - Kehilangan minat dalam aktivitas yang biasa dinikmati
                - Perubahan pola makan dan tidur
                - Kelelahan atau kehilangan energi
                - Kesulitan berkonsentrasi
                - Perasaan tidak berharga atau bersalah
                - Pikiran tentang kematian atau bunuh diri
                
                Penanganan depresi dapat meliputi:
                1. Psikoterapi
                2. Pengobatan antidepresan
                3. Perubahan gaya hidup
                4. Dukungan sosial
                5. Aktivitas fisik regular
                
                Jika Anda mengalami gejala depresi, penting untuk segera mencari bantuan profesional.
            """.trimIndent(),
            category = "Artikel",
            date = "2024-03-10",
            author = "Dr. Emily Rodriguez"
        ),
        ArticleModel(
            id = 4,
            image = R.drawable.img_bipolar,
            title = "Mengenal Bipolar",
            content = """
                Gangguan bipolar adalah kondisi kesehatan mental yang menyebabkan perubahan ekstrem dalam suasana hati, energi, dan kemampuan berpikir.
                
                Tipe-tipe episode dalam gangguan bipolar:
                
                1. Episode Mania:
                - Energi yang sangat tinggi
                - Berkurangnya kebutuhan tidur
                - Pikiran yang berpacu
                - Perilaku impulsif
                - Perasaan euforia berlebihan
                
                2. Episode Depresi:
                - Perasaan sedih yang mendalam
                - Kehilangan minat
                - Kelelahan
                - Perubahan pola makan dan tidur
                - Pikiran tentang kematian
                
                Penanganan bipolar biasanya melibatkan:
                - Pengobatan mood stabilizer
                - Psikoterapi
                - Dukungan keluarga
                - Manajemen gaya hidup
                
                Diagnosis dan penanganan dini sangat penting untuk mengelola gangguan bipolar dengan efektif.
            """.trimIndent(),
            category = "Artikel",
            date = "2024-03-15",
            author = "Dr. David Kim"
        )
    )

    val filteredArticles: List<ArticleModel>
        get() {
            return _articles.filter { article ->
                val matchesCategory = when (_selectedTab.value) {
                    0 -> true // Semua
                    1 -> article.category == "Artikel"
                    2 -> article.category == "Video"
                    else -> true
                }

                val matchesSearch = if (_searchQuery.value.isNotEmpty()) {
                    article.title.contains(_searchQuery.value, ignoreCase = true) ||
                            article.content.contains(_searchQuery.value, ignoreCase = true) ||
                            article.author.contains(_searchQuery.value, ignoreCase = true)
                } else {
                    true
                }

                matchesCategory && matchesSearch
            }
        }

    fun setSelectedTab(index: Int) {
        _selectedTab.value = index
    }

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun getArticleById(id: Int): ArticleModel? {
        return _articles.find { it.id == id }
    }


}