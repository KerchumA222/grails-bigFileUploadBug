// Place your Spring DSL code here
beans = {
    multipartResolver(AppMultipartResolver) {
        maxUploadSize = 36700160
    }
}
