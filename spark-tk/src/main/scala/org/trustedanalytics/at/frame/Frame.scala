package org.trustedanalytics.at.frame

import org.apache.spark.api.java.JavaRDD
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.Row
import org.trustedanalytics.at.frame.internal.BaseFrame
import org.trustedanalytics.at.frame.internal.ops._
import org.trustedanalytics.at.frame.internal.ops.binning.BinColumnTransform
import org.trustedanalytics.at.frame.internal.ops.sample.AssignSampleTransform
import org.trustedanalytics.at.frame.internal.ops.exportdata.ExportToCsvSummarization
import org.trustedanalytics.at.frame.internal.rdd.PythonJavaRdd

class Frame(frameRdd: RDD[Row], frameSchema: Schema) extends BaseFrame // params named "frameRdd" and "frameSchema" because naming them "rdd" and "schema" masks the base members "rdd" and "schema" in this scope
    with AddColumnsTransform
    with AssignSampleTransform
    with BinColumnTransform
    with CountSummarization
    with ExportToCsvSummarization
    with SaveSummarization
    with TakeSummarization {
  init(frameRdd, frameSchema)

  /**
   * (typically called from pyspark, with jrdd)
   * @param jrdd java array of Any
   * @param schema frame schema
   */
  def this(jrdd: JavaRDD[Array[Any]], schema: Schema) = {
    this(PythonJavaRdd.toRowRdd(jrdd.rdd, schema), schema)
  }
}
