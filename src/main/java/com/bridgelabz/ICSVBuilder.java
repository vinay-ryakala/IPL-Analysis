package com.bridgelabz;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public interface ICSVBuilder<E>
{
   public <E> Iterator<E> getCSVFileIterator(Reader reader, Class csvClass) throws CSVBuilderException;
   public  List getList(Reader reader, Class csvClass) throws CSVBuilderException;
}