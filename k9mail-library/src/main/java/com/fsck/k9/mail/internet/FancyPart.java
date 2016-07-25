package com.fsck.k9.mail.internet;


import android.support.annotation.Nullable;

import com.fsck.k9.mail.Part;
import org.apache.james.mime4j.dom.field.ContentDispositionField;
import org.apache.james.mime4j.dom.field.ContentTypeField;
import org.apache.james.mime4j.field.Fields;


public class FancyPart {
    private final ContentTypeField contentTypeField;
    private final ContentDispositionField contentDispositionField;


    public FancyPart(ContentTypeField contentTypeField, ContentDispositionField contentDispositionField) {
        this.contentTypeField = contentTypeField;
        this.contentDispositionField = contentDispositionField;
    }

    public static FancyPart from(Part part) {
        ContentTypeField contentTypeField = Fields.contentType(part.getContentType());
        ContentDispositionField contentDispositionField = Fields.contentDisposition(part.getDisposition());

        return new FancyPart(contentTypeField, contentDispositionField);
    }


    public String getMimeType() {
        return contentTypeField.getMimeType();
    }

    public String getBoundary() {
        return contentTypeField.getBoundary();
    }

    public String getContentTypeName() {
        return contentTypeField.getParameter("name");
    }

    public boolean isMultipart() {
        return contentTypeField.isMultipart();
    }

    public boolean isDispositionInline() {
        return contentDispositionField.isInline();
    }

    public boolean isDispositionAttachment() {
        return contentDispositionField.isAttachment();
    }

    public String getDispositionFilename() {
        return contentDispositionField.getFilename();
    }

    public Object getDispositionContentId() {
        return contentDispositionField.getParameter("id");
    }

    @Nullable
    public Long getDispositionSize() {
        long size = contentDispositionField.getSize();
        if (size == -1) {
            return null;
        }
        return size;
    }
}
