package foliocontrol.android.foliocontrolandroid.data.repository

import foliocontrol.android.foliocontrolandroid.domain.Partnership
import foliocontrol.android.foliocontrolandroid.domain.Premise
import foliocontrol.android.foliocontrolandroid.domain.Property
import foliocontrol.android.foliocontrolandroid.domain.PropertyDocument
import okhttp3.MultipartBody

/**
 * Interface defining property-related operations.
 */
interface PropertyService {
    /**
     * Suspended function to retrieve properties associated with a partnership.
     *
     * @param token The authentication token.
     * @param partnership The [Partnership] for which properties are to be retrieved.
     * @return List of [Property] objects associated with the specified partnership.
     */
    suspend fun getProperties(
        token: String,
        partnership: Partnership,
    ): List<Property>

    /**
     * Suspended function to save a new property along with its image.
     *
     * @param token The authentication token.
     * @param property The [Property] object to be saved.
     * @param propertyImage The property image as a [MultipartBody.Part].
     */
    suspend fun savePropertyByPropertyID(
        token: String,
        property: Property,
        propertyImage: MultipartBody.Part,
    )

    /**
     * Suspended function to retrieve details for a specific property.
     *
     * @param token The authentication token.
     * @param property The [Property] for which details are to be retrieved.
     * @return [Property] object representing the details of the specified property.
     */
    suspend fun getDetailsForProperty(
        token: String,
        property: Property,
    ): Property

    /**
     * Suspended function to retrieve documents associated with a specific property.
     *
     * @param token The authentication token.
     * @param property The [Property] for which documents are to be retrieved.
     * @return List of [PropertyDocument] objects associated with the specified property.
     */
    suspend fun getDocumentsForProperty(
        token: String,
        property: Property,
    ): List<PropertyDocument>

    /**
     * Suspended function to retrieve premises associated with a specific property.
     *
     * @param token The authentication token.
     * @param property The [Property] for which premises are to be retrieved.
     * @return List of [Premise] objects associated with the specified property.
     */
    suspend fun getPremisesForProperty(
        token: String,
        property: Property,
    ): List<Premise>

    /**
     * Suspended function to add a new property.
     *
     * @param token The authentication token.
     * @param property The [Property] object to be added.
     */
    suspend fun addProperty(
        token: String,
        property: Property,
    )

    /**
     * Suspended function to delete a property by its ID.
     *
     * @param token The authentication token.
     * @param propertyID The ID of the property to be deleted.
     */
    suspend fun deletePropertyByPropertyID(
        token: String,
        propertyID: Int,
    )

    /**
     * Suspended function to delete a document by its ID associated with a specific property.
     *
     * @param token The authentication token.
     * @param documentID The ID of the document to be deleted.
     */
    suspend fun deleteDocumentByPropertyID(
        token: String,
        documentID: Int,
    )

    /**
     * Suspended function to upload a document for a specific property.
     *
     * @param token The authentication token.
     * @param documentImage The document image as a [MultipartBody.Part].
     * @param document The [PropertyDocument] object to be uploaded.
     */
    suspend fun uploadDocument(
        token: String,
        documentImage: MultipartBody.Part,
        document: PropertyDocument,
    )
}
