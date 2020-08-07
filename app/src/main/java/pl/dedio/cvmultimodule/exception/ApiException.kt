package pl.dedio.cvmultimodule.exception

import java.lang.RuntimeException

class ApiException(val code: Int): RuntimeException()