package pl.dedio.cvmultimodule.base.useCase

 interface ParametrizedUseCase<out Response : Any, in Params> {

    suspend fun execute(params: Params): Response
}