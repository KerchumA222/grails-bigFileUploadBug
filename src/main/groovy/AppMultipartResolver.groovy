import org.apache.commons.fileupload.FileItem
import org.springframework.web.multipart.MaxUploadSizeExceededException
import org.springframework.web.multipart.MultipartException
import org.springframework.web.multipart.commons.CommonsFileUploadSupport.MultipartParsingResult
import org.springframework.web.multipart.commons.CommonsMultipartResolver
import javax.servlet.http.HttpServletRequest

class AppMultipartResolver extends CommonsMultipartResolver {
    static final String FILE_SIZE_EXCEEDED_ERROR = "fileSizeExceeded"

    @Override
    protected MultipartParsingResult parseRequest(HttpServletRequest request) throws MultipartException {
        String encoding = determineEncoding(request);
        try {
            return super.parseRequest(request);
        } catch (MaxUploadSizeExceededException e) {
            request.setAttribute(FILE_SIZE_EXCEEDED_ERROR, true)
            return parseFileItems(Collections.<FileItem> emptyList(), encoding);
        }
    }
}
