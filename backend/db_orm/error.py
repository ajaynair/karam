class DbError(BaseException):
    def __init__(self, *args) -> None:
        if args:
            self.message = args[0]
        else:
            self.message = None

    def __str__(self) -> str:
        if self.message:
            return 'DbError, {0} '.format(self.message)
        else:
            return 'DbError has been raised'
