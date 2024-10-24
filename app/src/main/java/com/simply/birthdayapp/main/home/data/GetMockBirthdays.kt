package com.simply.birthdayapp.main.home.data

import android.os.Build
import com.simply.birthdayapp.commondomain.model.Birthday
import java.time.LocalDateTime


fun getMockBirthdays(): List<Birthday> {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        listOf(
            Birthday(
                createdAt = LocalDateTime.now().minusDays(10).toString(),
                date = LocalDateTime.of(1990, 5, 10, 0, 0).toString(),
                id = 1,
                image = "https://s3-alpha-sig.figma.com/img/54a6/abbe/eadb372e3e013ab667401f489eaf9aa1?Expires=1730678400&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=aSJVU84B4XWFErfqgde1x5dZzWHgiI3yEXUXfUK27YGcocPOcmF115XEogJj7cWWD~Acxg3PZfrJ9xKiBw7cJPAI6PX95iJcLbEdTfwIr~TBPkHtjoiDNLHMvxEOp5Ons6MWAj-MmhptPe-4S04XlCDNhKMy4j-9eZh-O0QIYCB475sLdsZrVFyqN6k8hATvKT1~puoaQtHdJocWgMrU-ZbRArCtVBe64MJ2te6eZDEFlrI1adnyNGTUwwQlU-fl9FN42HakQVPvtinEk3mcJjKhkIjsPeKWMR-ADNdoTkWCzOIXh6w4oWAOG5ulSBOVS~UfaLubBUe~3LjQv~Q2pw__",
                message = "Happy Birthday, John!",
                name = "John Doe",
                relation = "Friend",
                upcomingAge = 34,
                upcomingBirthday = LocalDateTime.of(2024, 5, 10, 0, 0).toString(),
                updatedAt = LocalDateTime.now().toString(),
                userId = 1
            ),
            Birthday(
                createdAt = LocalDateTime.now().minusDays(20).toString(),
                date = LocalDateTime.of(1985, 7, 22, 0, 0).toString(),
                id = 2,
                image = "https://s3-alpha-sig.figma.com/img/2d3f/6ff7/e335622f79af37813d127fe6aabe50b5?Expires=1730678400&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=apgsF3B1j9qd1NJPRpqrjokBsPSzjgpocPYf~ThIDy~5vzpRidSgqrALgEcq138B9V0DtPzSZlJkKc2c95rhr-RlFKsbml6rmJFqAiTbSxpP2i-ise0SMnDnvMLW8XA05h8UuyorYYm03v~g4xTwkB2LnVonacRZm~ygeHKDcFu7uHIzzqtLTToEi5HtEgcV6EcIu4pQDvWdyCB7p0UherPNaow1ljIelbop57gvKRzwhfbAu9y87gZe57TBV1x7oCdjJsGHVBYs-FVTPW0jTYnsYmwXCnl4XA-c1QIFe3c-P6K~U~D-XRUYeESiIND8dP3S6I-7aQV591XCetWbLQ__",
                message = "Wishing you a fantastic day, Jane!",
                name = "Jane Smith",
                relation = "Colleague",
                upcomingAge = 39,
                upcomingBirthday = LocalDateTime.of(2024, 7, 22, 0, 0).toString(),
                updatedAt = LocalDateTime.now().toString(),
                userId = 2
            ),
            Birthday(
                createdAt = LocalDateTime.now().minusDays(30).toString(),
                date = LocalDateTime.of(2000, 12, 1, 0, 0).toString(),
                id = 3,
                image = "https://s3-alpha-sig.figma.com/img/5d0e/02aa/ee5e112e7830fe212e16348636179c84?Expires=1730678400&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=mGsxPaaItyf5k4yb4G~DJGDlChCBuXRlhp76RPQC~B9qPALnJIIMF8otBUsu35UDFlI8Ip6XwVNd8VoG0m9rJKpcV-fAUy3BzoExPwI2YbZPeLNJaqt1AV5V750Qojjy5rsxGChwl1YfwjcCG2C~F7XqCo6KDKsVN2UfMc1P5EXCIBmuLvsxZNFOY2naoS86nehuKbqQ3NCKrB2JjdhR9DB9AerQvrg0JE3JEnDGtGLtGZcbVKukq-aiCoHmID6-WfWJXjtYN4~NFu0K9MizeZGypCbqqo91KZKgGlj3nw1Id9-avsFr~6l4RVeRDt-fa7TvEMOtQKldUODRZWQR4Q__",
                message = "Have a great birthday, Alice!",
                name = "Alice Johnson",
                relation = "Sister",
                upcomingAge = 24,
                upcomingBirthday = LocalDateTime.of(2024, 12, 1, 0, 0).toString(),
                updatedAt = LocalDateTime.now().toString(),
                userId = 3
            ),
            Birthday(
                createdAt = LocalDateTime.now().minusDays(10).toString(),
                date = LocalDateTime.of(1990, 5, 10, 0, 0).toString(),
                id = 4,
                image = "https://s3-alpha-sig.figma.com/img/54a6/abbe/eadb372e3e013ab667401f489eaf9aa1?Expires=1730678400&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=aSJVU84B4XWFErfqgde1x5dZzWHgiI3yEXUXfUK27YGcocPOcmF115XEogJj7cWWD~Acxg3PZfrJ9xKiBw7cJPAI6PX95iJcLbEdTfwIr~TBPkHtjoiDNLHMvxEOp5Ons6MWAj-MmhptPe-4S04XlCDNhKMy4j-9eZh-O0QIYCB475sLdsZrVFyqN6k8hATvKT1~puoaQtHdJocWgMrU-ZbRArCtVBe64MJ2te6eZDEFlrI1adnyNGTUwwQlU-fl9FN42HakQVPvtinEk3mcJjKhkIjsPeKWMR-ADNdoTkWCzOIXh6w4oWAOG5ulSBOVS~UfaLubBUe~3LjQv~Q2pw__",
                message = "Happy Birthday, John!",
                name = "John Doe",
                relation = "Friend",
                upcomingAge = 34,
                upcomingBirthday = LocalDateTime.of(2024, 5, 10, 0, 0).toString(),
                updatedAt = LocalDateTime.now().toString(),
                userId = 4
            ),
            Birthday(
                createdAt = LocalDateTime.now().minusDays(20).toString(),
                date = LocalDateTime.of(1985, 7, 22, 0, 0).toString(),
                id = 5,
                image = "https://s3-alpha-sig.figma.com/img/2d3f/6ff7/e335622f79af37813d127fe6aabe50b5?Expires=1730678400&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=apgsF3B1j9qd1NJPRpqrjokBsPSzjgpocPYf~ThIDy~5vzpRidSgqrALgEcq138B9V0DtPzSZlJkKc2c95rhr-RlFKsbml6rmJFqAiTbSxpP2i-ise0SMnDnvMLW8XA05h8UuyorYYm03v~g4xTwkB2LnVonacRZm~ygeHKDcFu7uHIzzqtLTToEi5HtEgcV6EcIu4pQDvWdyCB7p0UherPNaow1ljIelbop57gvKRzwhfbAu9y87gZe57TBV1x7oCdjJsGHVBYs-FVTPW0jTYnsYmwXCnl4XA-c1QIFe3c-P6K~U~D-XRUYeESiIND8dP3S6I-7aQV591XCetWbLQ__",
                message = "Wishing you a fantastic day, Jane!",
                name = "Jane Smith",
                relation = "Colleague",
                upcomingAge = 39,
                upcomingBirthday = LocalDateTime.of(2024, 7, 22, 0, 0).toString(),
                updatedAt = LocalDateTime.now().toString(),
                userId = 5
            ),
            Birthday(
                createdAt = LocalDateTime.now().minusDays(30).toString(),
                date = LocalDateTime.of(2000, 12, 1, 0, 0).toString(),
                id = 6,
                image = "https://s3-alpha-sig.figma.com/img/5d0e/02aa/ee5e112e7830fe212e16348636179c84?Expires=1730678400&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=mGsxPaaItyf5k4yb4G~DJGDlChCBuXRlhp76RPQC~B9qPALnJIIMF8otBUsu35UDFlI8Ip6XwVNd8VoG0m9rJKpcV-fAUy3BzoExPwI2YbZPeLNJaqt1AV5V750Qojjy5rsxGChwl1YfwjcCG2C~F7XqCo6KDKsVN2UfMc1P5EXCIBmuLvsxZNFOY2naoS86nehuKbqQ3NCKrB2JjdhR9DB9AerQvrg0JE3JEnDGtGLtGZcbVKukq-aiCoHmID6-WfWJXjtYN4~NFu0K9MizeZGypCbqqo91KZKgGlj3nw1Id9-avsFr~6l4RVeRDt-fa7TvEMOtQKldUODRZWQR4Q__",
                message = "Have a great birthday, Alice!",
                name = "Alice Johnson",
                relation = "Sister",
                upcomingAge = 24,
                upcomingBirthday = LocalDateTime.of(2024, 12, 1, 0, 0).toString(),
                updatedAt = LocalDateTime.now().toString(),
                userId = 6
            ),
            Birthday(
                createdAt = LocalDateTime.now().minusDays(10).toString(),
                date = LocalDateTime.of(1990, 5, 10, 0, 0).toString(),
                id = 7,
                image = "https://s3-alpha-sig.figma.com/img/54a6/abbe/eadb372e3e013ab667401f489eaf9aa1?Expires=1730678400&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=aSJVU84B4XWFErfqgde1x5dZzWHgiI3yEXUXfUK27YGcocPOcmF115XEogJj7cWWD~Acxg3PZfrJ9xKiBw7cJPAI6PX95iJcLbEdTfwIr~TBPkHtjoiDNLHMvxEOp5Ons6MWAj-MmhptPe-4S04XlCDNhKMy4j-9eZh-O0QIYCB475sLdsZrVFyqN6k8hATvKT1~puoaQtHdJocWgMrU-ZbRArCtVBe64MJ2te6eZDEFlrI1adnyNGTUwwQlU-fl9FN42HakQVPvtinEk3mcJjKhkIjsPeKWMR-ADNdoTkWCzOIXh6w4oWAOG5ulSBOVS~UfaLubBUe~3LjQv~Q2pw__",
                message = "Happy Birthday, John!",
                name = "John Doe",
                relation = "Friend",
                upcomingAge = 34,
                upcomingBirthday = LocalDateTime.of(2024, 5, 10, 0, 0).toString(),
                updatedAt = LocalDateTime.now().toString(),
                userId = 7
            ),
            Birthday(
                createdAt = LocalDateTime.now().minusDays(20).toString(),
                date = LocalDateTime.of(1985, 7, 22, 0, 0).toString(),
                id = 8,
                image = "https://s3-alpha-sig.figma.com/img/2d3f/6ff7/e335622f79af37813d127fe6aabe50b5?Expires=1730678400&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=apgsF3B1j9qd1NJPRpqrjokBsPSzjgpocPYf~ThIDy~5vzpRidSgqrALgEcq138B9V0DtPzSZlJkKc2c95rhr-RlFKsbml6rmJFqAiTbSxpP2i-ise0SMnDnvMLW8XA05h8UuyorYYm03v~g4xTwkB2LnVonacRZm~ygeHKDcFu7uHIzzqtLTToEi5HtEgcV6EcIu4pQDvWdyCB7p0UherPNaow1ljIelbop57gvKRzwhfbAu9y87gZe57TBV1x7oCdjJsGHVBYs-FVTPW0jTYnsYmwXCnl4XA-c1QIFe3c-P6K~U~D-XRUYeESiIND8dP3S6I-7aQV591XCetWbLQ__",
                message = "Wishing you a fantastic day, Jane!",
                name = "Jane Smith",
                relation = "Colleague",
                upcomingAge = 39,
                upcomingBirthday = LocalDateTime.of(2024, 7, 22, 0, 0).toString(),
                updatedAt = LocalDateTime.now().toString(),
                userId = 8
            ),
            Birthday(
                createdAt = LocalDateTime.now().minusDays(30).toString(),
                date = LocalDateTime.of(2000, 12, 1, 0, 0).toString(),
                id = 9,
                image = "https://s3-alpha-sig.figma.com/img/5d0e/02aa/ee5e112e7830fe212e16348636179c84?Expires=1730678400&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=mGsxPaaItyf5k4yb4G~DJGDlChCBuXRlhp76RPQC~B9qPALnJIIMF8otBUsu35UDFlI8Ip6XwVNd8VoG0m9rJKpcV-fAUy3BzoExPwI2YbZPeLNJaqt1AV5V750Qojjy5rsxGChwl1YfwjcCG2C~F7XqCo6KDKsVN2UfMc1P5EXCIBmuLvsxZNFOY2naoS86nehuKbqQ3NCKrB2JjdhR9DB9AerQvrg0JE3JEnDGtGLtGZcbVKukq-aiCoHmID6-WfWJXjtYN4~NFu0K9MizeZGypCbqqo91KZKgGlj3nw1Id9-avsFr~6l4RVeRDt-fa7TvEMOtQKldUODRZWQR4Q__",
                message = "Have a great birthday, Alice!",
                name = "Alice Johnson",
                relation = "Sister",
                upcomingAge = 24,
                upcomingBirthday = LocalDateTime.of(2024, 12, 1, 0, 0).toString(),
                updatedAt = LocalDateTime.now().toString(),
                userId = 9
            ),
        )
    } else {
        emptyList()
    }
}
