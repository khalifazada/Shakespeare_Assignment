import scala.io.Source
import scala.collection.mutable.ListBuffer
import java.io.{File, PrintWriter}

object Shakespeare {
	def main(args: Array[String]) {

		// Read
		val raw_text = Source.fromFile("shakespeare short.txt").getLines.toList

		// Map
		var word_list = ListBuffer[String]()
		for (i <- 0 to raw_text.length-1) {
			var temp_list = raw_text(i).mkString.toLowerCase.split("[ !?:;,.()*]+").toList
			
			for (j <- 0 to temp_list.length-1) {				
				word_list += temp_list(j)	
			}
		}

		// Reduce		
		val dictionary = word_list.groupBy(identity).mapValues(_.size).toList
		
		// Write
		val writer = new PrintWriter(new File("word_count_scala.csv"))
		var csvData = new StringBuffer("")
		
		for(k <- 0 to dictionary.length-1) {
			csvData.append(dictionary(k)[0])
			csvData.append(",")
			csvData.append(dictionary(k)[1])
			// val str = for ((k,v) <- dictionary) yield s"$k\t$v"  
		
		/*		
		// Create file writer
		val pw = new java.io.PrintWriter(new File("mapping.csv"))  
		// Write each map entry in new line and close
		try pw.write(str.mkString("\n")) finally pw.close()
		*/
		}
		writer.write(csvData.toString())
		writer.close()
	}
}
