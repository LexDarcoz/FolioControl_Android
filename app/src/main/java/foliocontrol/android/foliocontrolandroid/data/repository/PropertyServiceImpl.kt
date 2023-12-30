package foliocontrol.android.foliocontrolandroid.data.repository

import foliocontrol.android.foliocontrolandroid.data.remote.createProperty
import foliocontrol.android.foliocontrolandroid.data.remote.deleteDocumentByID
import foliocontrol.android.foliocontrolandroid.data.remote.deletePropertyById
import foliocontrol.android.foliocontrolandroid.data.remote.fetchDocuments
import foliocontrol.android.foliocontrolandroid.data.remote.fetchPremises
import foliocontrol.android.foliocontrolandroid.data.remote.fetchProperties
import foliocontrol.android.foliocontrolandroid.data.remote.fetchProperty
import foliocontrol.android.foliocontrolandroid.data.remote.savePropertyByID
import foliocontrol.android.foliocontrolandroid.data.remote.uploadDocumentByPropertyID
import foliocontrol.android.foliocontrolandroid.domain.Partnership
import foliocontrol.android.foliocontrolandroid.domain.Premise
import foliocontrol.android.foliocontrolandroid.domain.Property
import foliocontrol.android.foliocontrolandroid.domain.PropertyDocument
import okhttp3.MultipartBody

/**
 * Implementation of the [PropertyService] interface providing property-related operations.
 */
class PropertyServiceImpl : PropertyService {
    /**
     * Suspended function to retrieve properties associated with a partnership.
     *
     * @param token The authentication token.
     * @param partnership The [Partnership] for which properties are to be retrieved.
     * @return List of [Property] objects associated with the specified partnership.
     */
    override suspend fun getProperties(
        token: String,
        partnership: Partnership,
    ): List<Property> {
        return fetchProperties(token, partnership.partnershipID)
    }

    /**
     * Suspended function to save a new property along with its image.
     *
     * @param token The authentication token.
     * @param property The [Property] object to be saved.
     * @param propertyImage The property image as a [MultipartBody.Part].
     */
    override suspend fun savePropertyByPropertyID(
        token: String,
        property: Property,
        propertyImage: MultipartBody.Part,
    ) {
        savePropertyByID(
            token,
            property,
            propertyImage,
        )
    }

    /**
     * Suspended function to retrieve details for a specific property.
     *
     * @param token The authentication token.
     * @param property The [Property] for which details are to be retrieved.
     * @return [Property] object representing the details of the specified property.
     */
    override suspend fun getDetailsForProperty(
        token: String,
        property: Property,
    ): Property {
        return fetchProperty(token, property.propertyID)
    }

    /**
     * Suspended function to retrieve documents associated with a specific property.
     *
     * @param token The authentication token.
     * @param property The [Property] for which documents are to be retrieved.
     * @return List of [PropertyDocument] objects associated with the specified property.
     */
    override suspend fun getDocumentsForProperty(
        token: String,
        property: Property,
    ): List<PropertyDocument> {
        return fetchDocuments(token, property.propertyID)
    }

    /**
     * Suspended function to retrieve premises associated with a specific property.
     *
     * @param token The authentication token.
     * @param property The [Property] for which premises are to be retrieved.
     * @return List of [Premise] objects associated with the specified property.
     */
    override suspend fun getPremisesForProperty(
        token: String,
        property: Property,
    ): List<Premise> {
        return fetchPremises(token, property.propertyID)
    }

    /**
     * Suspended function to add a new property.
     *
     * @param token The authentication token.
     * @param property The [Property] object to be added.
     */
    override suspend fun addProperty(
        token: String,
        property: Property,
    ) {
        createProperty(token, property)
    }

    /**
     * Suspended function to delete a property by its ID.
     *
     * @param token The authentication token.
     * @param propertyID The ID of the property to be deleted.
     */
    override suspend fun deletePropertyByPropertyID(
        token: String,
        propertyID: Int,
    ) {
        deletePropertyById(token, propertyID)
    }

    /**
     * Suspended function to delete a document by its ID associated with a specific property.
     *
     * @param token The authentication token.
     * @param documentID The ID of the document to be deleted.
     */
    override suspend fun deleteDocumentByPropertyID(
        token: String,
        documentID: Int,
    ) {
        deleteDocumentByID(token, documentID)
    }

    /**
     * Suspended function to upload a document for a specific property.
     *
     * @param token The authentication token.
     * @param documentImage The document image as a [MultipartBody.Part].
     * @param document The [PropertyDocument] object to be uploaded.
     */
    override suspend fun uploadDocument(
        token: String,
        documentImage: MultipartBody.Part,
        document: PropertyDocument,
    ) {
        uploadDocumentByPropertyID(token, documentImage, document)
    }
}
