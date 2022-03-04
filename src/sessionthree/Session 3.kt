package sessionthree

/**
 * Session 3 : Abstraction Layers and interfaces
 *
 * 1) Abstraction : is the concept of generalising an idea that's consistent with all the
 * software pattern units.
 *
 * Examples :
 * - Any architectural building requires a Base or an Infrastructure, there are
 * various types of buildings such as : Schools, Universities, Railway Stations, Residential buildings...etc,
 * but, they are overall civil buildings and they can be categorized under a single Building class, that is
 * the abstraction in its core.
 *
 * And the Schools/Universities can be further categorized under the Educational buildings.
 *
 * And the RailwayStations/BusStations can be further categorized under Station building which is
 * even categorized under Assembly Building.
 *
 * And so on and so forth.....these layers of categorization are known as abstraction layers and their usage is
 * to add new functions to the descendants along the hierarchy.
 *
 * - Knowing these basic concepts we could build some civil buildings using a ConstructionSite factory class that
 *  creates instances of buildings for us and this is known as THE ABSTRACT FACTORY PATTERN.
 *
 * @author pavl_g.
 */

/**
 * Represents the base of a building.
 *
 * Any building should extend this type.
 *
 * @param name the name of the building.
 *
 * @author pavl_g.
 */
abstract class Building protected constructor(val name: String) {
    lateinit var licenceType: ConstructionSite.LicenceType
    lateinit var licence: String

    abstract fun getType(): String
    abstract fun getWidth(): Double
    abstract fun getHeight(): Double
    abstract fun getLength(): Double

    abstract fun getNumberOfBricks(): Long
    abstract fun getTypeOfBricks(): String

    abstract fun getTypeOfConcrete(): String
    abstract fun getNumberOfConcrete(): Long

    abstract fun getNumberOfCorners(): Long

    fun setLicence(licenceType: ConstructionSite.LicenceType, licence: String) {
        this.licenceType = licenceType
        this.licence = licence
    }
}

/**
 * An extendable Utility for constructing a civil building.
 *
 * @author pavl_g.
 */
open class ConstructionSite private constructor() {

    enum class LicenceType(var anchorNumber: Int) {
        School(500),
        University(600),
        Hospital(750),
        Station(123);
    }

    enum class School {
        PublicSchool,
        PrivateSchool,
        LanguageSchool
    }
    enum class University {
        SohagUniversity,
        MeritUniversity,
        BritishUniversity
    }

    /**
     * Companion Objets are objects that are instantiated in accompany with
     * the beholding class and don't need a class object to be called, remember
     * the trick is making this class as private which inhibits the developer from creating an
     * object.
     */
    companion object {

        /**
         * Constructs and licences a School.
         *
         * @param building a reference to a constructed and licenced building.
         */
        fun constructSchool(school: ConstructionSite.School, name: String): sessionthree.School {
            val building: sessionthree.School
            when (school) {
                School.LanguageSchool -> {
                    building = LanguageSchool(name)
                    val languages = arrayOf("Eng", "Span")
                    building.setAvailableLanguages(languages)
                }
                else -> {
                    building = PublicSchool(name)
                }
            }
            building.setLicence(LicenceType.School, "$name ${LicenceType.School.anchorNumber}")
            return building
        }
		/**
         * Constructs and licences a University.
         *
         * @param building a reference to a constructed and licenced building.
         */
        fun constructUniversity(university: ConstructionSite.University): sessionthree.University {
            val building: sessionthree.University
            when (university) {
                University.BritishUniversity -> {
                    building = BritishUniversity()
                    building.availableFaculties = arrayOf("Pharmacy", "Dentistry", "Engineering", "Economy")
                }
                else -> {
                    building = SohagUniversity()
                    building.availableFaculties = arrayOf("Medicine", "Pharmacy", "Computer Science", "Engineering")
                }
            }
            building.setLicence(LicenceType.University, "${building.name} ${LicenceType.School.anchorNumber}")
            return building
        }
		
    }
}

/**
 * Now let's start implementing our Abstract class Building.
 */

/**
 * Resembles an educational building.
 *
 * Schools and Universities should extend this entity.
 *
 * @author pavl_g.
 */
abstract class EducationalBuilding(name: String) : Building(name) {
    override fun getType(): String {
        return "Educational Building"
    }
}

/**
 * Resembles the School building.
 *
 * Any School building can extend this class for extra features.
 *
 * @author pavl_g.
 */
abstract class School(name: String): EducationalBuilding(name) {
    /**
     * Adds a postfix School to the main type.
     * @return the main building type postfixed with School.
     */
    override fun getType(): String {
        return super.getType() + " : " + "School"
    }

    /**
     * Retrieves the type of bricks used for build School buildings.
     * @return a representative String of type of bricks.
     */
    override fun getTypeOfBricks(): String {
        return "Bricky 342"
    }

    /**
     * Retrieves the type of concrete used for building Schools.
     * @return the type of concrete used in String.
     */
    override fun getTypeOfConcrete(): String {
        return "Steel A1"
    }
}

/**
 * Instantiates a public school.
 *
 * @author pavl_g.
 */
open class PublicSchool(name: String): School(name) {

    override fun getWidth(): Double {
        return 300.0
    }

    override fun getHeight(): Double {
        return 500.0
    }

    override fun getLength(): Double {
        return 500.0
    }

    override fun getNumberOfBricks(): Long {
        return 4000
    }

    override fun getNumberOfConcrete(): Long {
        return 300
    }

    override fun getNumberOfCorners(): Long {
        return 50
    }
}

class EastwoodSchool: PublicSchool("Eastwood School for Boys") {
    override fun getWidth(): Double {
        return super.getWidth() + 100
    }

    override fun getHeight(): Double {
        return super.getHeight() + 10
    }

    override fun getLength(): Double {
        return super.getLength() + 100
    }

    override fun getNumberOfBricks(): Long {
        return getNumberOfBricks() + 30
    }

    override fun getNumberOfConcrete(): Long {
        return super.getNumberOfConcrete() + 20
    }

    override fun getNumberOfCorners(): Long {
        return super.getNumberOfCorners() + 5
    }

}

/**
 * Instantiates a language school.
 *
 * @author pavl_g.
 */
open class LanguageSchool(name: String): School(name) {
    lateinit var languages: Array<String>

    override fun getWidth(): Double {
        return 300.0
    }

    override fun getHeight(): Double {
        return 500.0
    }

    override fun getLength(): Double {
        return 500.0
    }

    override fun getNumberOfBricks(): Long {
        return 4000
    }

    override fun getNumberOfConcrete(): Long {
        return 300
    }

    override fun getNumberOfCorners(): Long {
        return 50
    }

    fun setAvailableLanguages(languages: Array<String>) {
        this.languages = languages
    }

    fun getAvailableLanguages(): Array<String> {
        return languages
    }
}

/**
 * Base class for building universities.
 *
 * Any University should extend this class.
 *
 * @author pavl_g.
 */
abstract class University(name: String): EducationalBuilding(name) {
    lateinit var availableFaculties: Array<String>
    /**
     * Adds a postfix University to the main type.
     * @return the main building type postfixed with University.
     */
    override fun getType(): String {
        return super.getType() + " : " + "University"
    }

    /**
     * Retrieves the type of bricks used for build University buildings.
     * @return a representative String of type of bricks.
     */
    override fun getTypeOfBricks(): String {
        return "Bricky 111"
    }

    /**
     * Retrieves the type of concrete used for building Universities.
     * @return the type of concrete used in String.
     */
    override fun getTypeOfConcrete(): String {
        return "Metallic A2"
    }
}

class SohagUniversity: University("Sohag University") {
    override fun getWidth(): Double {
        return 600.0
    }

    override fun getHeight(): Double {
        return 500.0
    }

    override fun getLength(): Double {
        return 500.0
    }

    override fun getNumberOfBricks(): Long {
        return 50000
    }

    override fun getNumberOfConcrete(): Long {
        return 100000
    }

    override fun getNumberOfCorners(): Long {
        return 1000
    }
}

open class BritishUniversity: University("British University") {
    override fun getWidth(): Double {
        return 6000.0
    }

    override fun getHeight(): Double {
        return 5000.0
    }

    override fun getLength(): Double {
        return 5000.0
    }

    override fun getNumberOfBricks(): Long {
        return 500000
    }

    override fun getNumberOfConcrete(): Long {
        return 1000000
    }

    override fun getNumberOfCorners(): Long {
        return 10000
    }
}


fun main() {
    // now let's test our abstraction layers

    // Recall that we want to build a basic public school
    val basicSchool =
            ConstructionSite.constructSchool(ConstructionSite.School.PublicSchool, "Basic School")
    println(basicSchool.name)
    println(basicSchool.licence)
    println(basicSchool.getHeight())
    println(basicSchool.getWidth())

    println()

    // build a language school
    val languageSchool =
            ConstructionSite.constructSchool(ConstructionSite.School.LanguageSchool, "Public Language School")
    println(languageSchool.name)
    println(languageSchool.licence)
    println(languageSchool.getHeight())
    println(languageSchool.getWidth())
    (languageSchool as LanguageSchool).getAvailableLanguages().forEach {
        println(it)
    }

    println()

    // build Sohag University instance
    val sohagUniversity =
            ConstructionSite.constructUniversity(ConstructionSite.University.SohagUniversity)
    println(sohagUniversity.name)
    println(sohagUniversity.licence)
    println(sohagUniversity.getHeight())
    println(sohagUniversity.getWidth())
    sohagUniversity.availableFaculties.forEach {
        println(it)
    }

    println()

    // without using the ConstructionSite class as its optional and mandatory in some cases.
    // if we want to build a school let's say with default properties then a utility that builds
    // our objects may be our way to go
    // let's build a custom school
    val eastwoodSchool = EastwoodSchool()
    eastwoodSchool.setLicence(ConstructionSite.LicenceType.School, eastwoodSchool.getType() + " : Private Custom")
    println(eastwoodSchool.name)
    println(eastwoodSchool.licence)
    println(eastwoodSchool.getHeight())
    println(eastwoodSchool.getWidth())


}
