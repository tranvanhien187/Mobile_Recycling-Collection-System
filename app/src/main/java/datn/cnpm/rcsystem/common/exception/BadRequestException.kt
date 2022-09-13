package datn.cnpm.rcsystem.common.exception

import datn.cnpm.rcsystem.common.ErrorCode


class BadRequestException(val error: ErrorCode) : RuntimeException()
