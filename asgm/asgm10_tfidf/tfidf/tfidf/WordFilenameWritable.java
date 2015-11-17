package edu.stthomas.gps.tfidf;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;

public class WordFilenameWritable implements WritableComparable<WordFilenameWritable> {

	private String word;
	private String fileName;

	public WordFilenameWritable() {
	}

	public WordFilenameWritable(String word, String fileName) {
		this.word = word;
		this.fileName = fileName;
	}

	public void set(String word, String fileName) {
		this.word = word;
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
	}

	public String getWord() {
		return word;
	}

	public void readFields(DataInput in) throws IOException {
		word = in.readUTF();
		fileName = in.readUTF();
	}

	public void write(DataOutput out) throws IOException {
		out.writeUTF(word);
		out.writeUTF(fileName);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((fileName == null) ? 0 : fileName.hashCode());
		result = prime * result + ((word == null) ? 0 : word.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WordFilenameWritable other = (WordFilenameWritable) obj;
		if (fileName == null) {
			if (other.fileName != null)
				return false;
		} else if (!fileName.equals(other.fileName))
			return false;
		if (word == null) {
			if (other.word != null)
				return false;
		} else if (!word.equals(other.word))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return word + "," + fileName;
	}

	@Override
	public int compareTo(WordFilenameWritable wfw) {
		int cmp = word.compareTo(wfw.word);
		if (cmp != 0) {
			return cmp;
		}
		return fileName.compareTo(wfw.fileName);
	}

}
