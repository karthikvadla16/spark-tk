
def bin_column(self, column_name, cutoffs, include_lowest=True, strict_binning=False, bin_column_name=None):
        """bin_column doc... excruciatingly well-written"""
        self._scala.binColumn(column_name,
                              self._tc.jutils.convert.to_scala_list_double([float(c) for c in cutoffs]),
                              include_lowest,
                              strict_binning,
                              self._tc.jutils.convert.to_scala_option(bin_column_name))

