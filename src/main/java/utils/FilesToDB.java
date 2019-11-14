package utils;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class FilesToDB {

	private static final String DIR = System.getProperty("user.dir");
	private static final String SQLITE_CONNECTION = "jdbc:sqlite:" + DIR + "books.db";
	private static Connection DBConnect;
	private static int count = 0;

	private static Connection getDBConnection() {

		if (DBConnect == null) {

			try {

				DBConnect = DriverManager.getConnection(SQLITE_CONNECTION);
				return DBConnect;

			} catch (SQLException e) {

				System.out.println(e.getMessage());
			}
		}

		return DBConnect;

	}

	private static List<String> getOnlyPDFs(File f) {

		return Arrays.stream(Objects.requireNonNull(f.list()))
												.filter(  a ->	a.endsWith(".pdf")  ||
														a.endsWith(".mobi") ||
														a.endsWith(".azw3") ||
														a.endsWith(".html") ||
														a.endsWith(".epub") ||
														a.endsWith(".docx") ||
														a.endsWith(".txt")  ||
														a.endsWith(".zip"))
				.collect(Collectors.toList());
	}

	private static List<File> getAllDirectories(File f) {

		return Arrays.stream(Objects.requireNonNull(f.listFiles())).filter(File::isDirectory).collect(Collectors.toList());
	}

	private static void saveAllFileInsideTheFolderToDB(File f, String tableName) {
	
		if(f.isDirectory()) {
			
			List<File> listOfDirecotries = getAllDirectories(f);
			List<String> listOfPDFs = getOnlyPDFs(f);
			listOfPDFs.forEach(a -> saveInDB(tableName, 1, a, a.substring(a.lastIndexOf('.')+1), f.getName()));
			listOfDirecotries.forEach(dir -> saveAllFileInsideTheFolderToDB(dir, tableName));
		}	
		else {
			
			System.err.println(f.getAbsolutePath()+" Is Not A Directory");
		}
	}

	private static int j = 0;

	private static void saveInDB(String tableName, Object... args) {

		String query = "INSERT INTO '"+tableName+"' VALUES (?, ?, ?, ?)";

		try {
			
			Connection conn = getDBConnection();
			PreparedStatement state = conn.prepareStatement(query);

			args[0] = j;

			for (int i = 0; i < args.length; i++) {

				state.setObject((i + 1), args[i]);
			}

			int rows = state.executeUpdate();
			count = count + rows;
			System.out.println(count + " Row/Rows Affected!");

		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		j++;
	}

	public static void main(String[] args) throws SQLException {

		File f = new File(DIR);
		
		
//		getOnlyPDFs(f).stream().forEach(a -> {
//
//			FilesToDB.saveInDB(1, a, "random");
//
//		});
		
		saveAllFileInsideTheFolderToDB(f, "myBooks_25oct19");

		System.out.println("-- COMPLETED --");
	}
}
